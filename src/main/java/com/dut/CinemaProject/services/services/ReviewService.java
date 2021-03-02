package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Review;
import com.dut.CinemaProject.dao.repos.ReviewRepository;
import com.dut.CinemaProject.services.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void createReview(Review review) {
        if(review.getText().isEmpty())
            throw new RuntimeException("text is empty");

        reviewRepository.save(review);
    }

    @Override
    public Optional<Review> getById(Long id) {
        return reviewRepository.findById(id);
    }

}
