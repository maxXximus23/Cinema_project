package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    //@Query("SELECT s FROM Session s WHERE s.date > NOW()")
    @Query(value = "SELECT * FROM (SELECT * FROM sessions WHERE date > NOW() ORDER BY date) ses GROUP BY ses.movie_id",
            nativeQuery = true)
    List<Session> getActualSessions();
}
