package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Hall;
import com.dut.CinemaProject.dao.domain.Movie;
import com.dut.CinemaProject.dao.domain.Session;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.HallRepository;
import com.dut.CinemaProject.dao.repos.MovieRepository;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dto.Session.NewSession;
import com.dut.CinemaProject.dto.Session.SessionDto;
import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.dto.Session.UpdateSessionData;
import com.dut.CinemaProject.dto.Ticket.Place;
import com.dut.CinemaProject.exceptions.BadRequestException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SessionService implements ISessionService {
    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<SessionDto> getActualSessions() {
        return sessionRepository.getActualSessions()
               .stream()
               .map(SessionDto::new)
               .collect(Collectors.toList());
    }

    @Override
    public SessionTicketsList getSessionTicketsData(Long sessionId) {
        Session sessionDb = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ItemNotFoundException("There are no such session"));

        List<Ticket> list = ticketRepository.findTicketsBySession(sessionDb);

        List<Place> tickets = list.stream()
                .map(ticket -> new Place(ticket.getRow(), ticket.getPlace()))
                .collect(Collectors.toList());

        return new SessionTicketsList(sessionId, sessionDb.getHall().getRowsAmount(),
                                    sessionDb.getHall().getPlaces(), tickets);
   }

    @Override
    public SessionDto getSession(Long id) {
        return new SessionDto(
                sessionRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException("Session not found"))
        );
    }

    @Override
    public Long createSession(NewSession sessionData) {
        Hall hallDb = hallRepository.findById(
                    sessionData.getHallId())
                        .orElseThrow(() -> new BadRequestException("There is no hall with given ID")
                );

        Movie movieDb = movieRepository.findById(
                    sessionData.getMovieId())
                        .orElseThrow(() -> new BadRequestException("There is no movie with given ID")
                );

        if (!isDateAcceptable(sessionData.getHallId(), sessionData.getDate(), movieDb.getDuration()))
            throw new BadRequestException("Invalid date");

        Session session = new Session();
        session.setHall(hallDb);
        session.setMovie(movieDb);
        session.setDate(sessionData.getDate());

        return sessionRepository.save(session).getId();
    }

    @Override
    public void removeSession(Long id) {
        Session sessionDb = sessionRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("There is no session with given ID"));

        ticketRepository.deleteAll(ticketRepository.findTicketsBySession(sessionDb));
        sessionRepository.delete(sessionDb);
    }

    @Override
    public SessionDto updateSession(Long id, UpdateSessionData sessionData) {
        Session session = sessionRepository.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("There is not session with given ID")
                );

        session.setHall(
                hallRepository.findById(
                    sessionData.getHallId())
                        .orElseThrow(() -> new BadRequestException("There is no hall with given ID"))
        );

        session.setMovie(
                movieRepository.findById(
                    sessionData.getMovieId())
                        .orElseThrow(() -> new BadRequestException("There is no movie with given ID"))
        );

        if (!isDateAcceptable(sessionData.getHallId(), sessionData.getDate(), session.getMovie().getDuration(), session.getId()))
            throw new BadRequestException("Invalid date");
        else
            session.setDate(sessionData.getDate());

        return new SessionDto(sessionRepository.save(session));
    }

    private Boolean isDateAcceptable(Long hallId, LocalDateTime date, Integer movieDuration){
        if (date.isBefore(LocalDateTime.now()))
            return false;

        List<Session> sessions = sessionRepository.getActualSessionsByHallId(hallId);

        for (Session session : sessions){
            if ((session.getDate()
                    .plusSeconds(session.getMovie()
                            .getDuration())
                    .isAfter(date)
                && session.getDate()
                    .isBefore(date))
            || (date.isBefore(session.getDate())
                && date.plusSeconds(movieDuration)
                    .isAfter(session.getDate())))
                return false;
        }
        return true;
    }

    private Boolean isDateAcceptable(Long hallId, LocalDateTime date, Integer movieDuration, Long sessionId){
        if (date.isBefore(LocalDateTime.now()))
            return false;

        List<Session> sessions = sessionRepository.getActualSessionsByHallId(hallId);

        sessions.removeIf(el -> el.getId().equals(sessionId));

        for (Session session : sessions){
            if ((session.getDate()
                    .plusSeconds(session.getMovie()
                            .getDuration())
                    .isAfter(date)
                    && session.getDate()
                    .isBefore(date))
                    || (date.isBefore(session.getDate())
                    && date.plusSeconds(movieDuration)
                    .isAfter(session.getDate())))
                return false;
        }
        return true;
    }
}
