package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Ticket.TicketDto;

public interface ITicketService {
    TicketDto getTicketById(Long id);
    void deleteTicket(Long id);
}
