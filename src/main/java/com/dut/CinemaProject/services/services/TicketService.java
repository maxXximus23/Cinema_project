package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;

    @Override
    public TicketDto getTicketById(Long id) {
        return new TicketDto(ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found")));
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket t = ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found"));

        ticketRepository.delete(t);
    }
}
