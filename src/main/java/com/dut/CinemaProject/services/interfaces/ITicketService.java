package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dao.domain.Ticket;

import java.util.Optional;

public interface ITicketService {
    Optional<Ticket> getTicketById(Long id);
    void deleteTicket(Long id);
}
