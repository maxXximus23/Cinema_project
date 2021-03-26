package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByIsBlocked(Boolean isBlocked);
}
