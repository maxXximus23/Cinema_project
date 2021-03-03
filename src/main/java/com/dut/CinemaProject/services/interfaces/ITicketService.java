package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Ticket.PurchaseTicketDto;
import com.dut.CinemaProject.dto.Ticket.TicketDto;

import java.util.List;

public interface ITicketService {
    List<TicketDto> getUsersTickets(Long userId);
    TicketDto getTicketById(Long id);
    void deleteTicket(Long id);
    Long purchaseTicket(PurchaseTicketDto purchaseTicketDto);
}
