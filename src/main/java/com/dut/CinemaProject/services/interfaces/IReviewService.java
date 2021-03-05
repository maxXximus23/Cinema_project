package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dao.domain.Review;
import com.dut.CinemaProject.dto.Review.NewReview;

public interface IReviewService {

    Long createReview(NewReview newReview);
    Review getById(Long id);
}
