package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Session;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByCustomer(User customer);
    List<Ticket> findTicketsBySession(Session session);
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.session.id = :sessionId")
    Integer countTicketsBySessionId(Long sessionId);
    @Query("SELECT t FROM Ticket t WHERE t.session.id = :sessionId AND t.place = :place AND t.row = :row")
    Optional<Ticket> getTicketByDetails(Integer place, Integer row, Long sessionId);
}
