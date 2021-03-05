package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dao.domain.Ticket;

public interface ITicketService {
    Ticket getTicketById(Long id);
    void deleteTicket(Long id);
}
