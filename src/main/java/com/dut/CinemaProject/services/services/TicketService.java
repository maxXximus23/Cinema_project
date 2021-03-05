package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.services.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.delete(getTicketById(id));
    }
}
