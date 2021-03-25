package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);
}
