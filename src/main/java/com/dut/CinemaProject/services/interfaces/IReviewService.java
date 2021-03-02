package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dao.domain.Review;

import java.util.Optional;


public interface IReviewService {

    void createReview(Review review);
    Optional<Review> getById(Long id);
}
