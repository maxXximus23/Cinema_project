package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
