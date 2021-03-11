package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Hall;
import com.dut.CinemaProject.dao.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s  FROM Session s WHERE s.date > NOW() GROUP BY s.movie_id")
    List<Session> getActualSessions();
    List<Session> findSessionsByHall(Hall hall);
    @Query(value="SELECT s FROM Session s WHERE s.date > NOW() AND s.hall_id = :hallId", nativeQuery = true)
    List<Session> getActualSessionsByHallId(@Param("hallId") Long hallId);
    @Query(value="SELECT s FROM Session s WHERE s.date > NOW() AND s.movie_id = :movieId", nativeQuery = true)
    List<Session> getActualSessionsByMovieId(@Param("movieId") Long movieId);
}
