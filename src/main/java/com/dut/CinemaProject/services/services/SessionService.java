package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Session;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class SessionService implements ISessionService {
    private SessionRepository sessionRepository;
    private TicketRepository ticketRepository;
    @Override
    public SessionTicketsList getSessionTicketsData(Long sessionId) {
        Session sessionDb = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ItemNotFoundException("There are no such session"));

        List<Ticket> list = ticketRepository.findTicketsBySession(sessionDb);

        HashMap<Integer, Integer> tickets = new HashMap<>();

        for (Ticket t: list)
            tickets.put(t.getRow(), t.getPlace());

        return new SessionTicketsList(sessionId, sessionDb.getHall().getRowsAmount(),
                                    sessionDb.getHall().getPlaces(), tickets);
    }
}
