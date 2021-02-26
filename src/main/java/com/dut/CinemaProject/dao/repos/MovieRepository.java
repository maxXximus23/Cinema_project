package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
