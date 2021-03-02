package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByUser(User customer);
}
