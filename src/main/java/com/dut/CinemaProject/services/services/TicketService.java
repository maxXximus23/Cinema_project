package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.services.interfaces.ITicketService;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void deleteTicket(Long id) {
        var ticket = ticketRepository.findById(id);
        if(ticket.isPresent())
            ticketRepository.deleteById(id);
    }
}
