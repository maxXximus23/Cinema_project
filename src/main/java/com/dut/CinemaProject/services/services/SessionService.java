package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Session;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.dto.Ticket.Place;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.dto.Session.SessionDto;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SessionService implements ISessionService {
    private SessionRepository sessionRepository;
    private TicketRepository ticketRepository;
    
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
}
