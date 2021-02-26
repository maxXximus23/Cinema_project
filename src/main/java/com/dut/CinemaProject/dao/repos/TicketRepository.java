package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
