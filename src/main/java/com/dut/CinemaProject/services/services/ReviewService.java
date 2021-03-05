package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Movie;
import com.dut.CinemaProject.dao.domain.Review;
import com.dut.CinemaProject.dao.repos.MovieRepository;
import com.dut.CinemaProject.dao.repos.ReviewRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.Review.NewReview;
import com.dut.CinemaProject.dto.Review.ReviewDto;
import com.dut.CinemaProject.services.exceptions.BadRequestException;
import com.dut.CinemaProject.services.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Override
    public Long createReview(NewReview newReview){
        var user = userRepository.findById(newReview.getAuthorId());
        if(user.isEmpty())
            throw new ItemNotFoundException("such user does not exist");
        var movie = movieRepository.findById(newReview.getMovieId());
        if(movie.isEmpty())
            throw new ItemNotFoundException("such movie does not exist");
        if(newReview.getText().isBlank())
            throw new BadRequestException("text is empty");

        Review review = new Review();
        review.setAuthor(user.get());
        review.setMovie(movie.get());
        review.setText(newReview.getText());
        review.setCreationDate(LocalDateTime.now());

        return reviewRepository.save(review).getId();
    }

    @Override
    public ReviewDto getById(Long id) {
        return new ReviewDto(reviewRepository.findById(id)
                .orElseThrow(() -> new com.dut.CinemaProject.exceptions.ItemNotFoundException("Review not found")));
    }

    @Override
    public List<ReviewDto> getReviewsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(ItemNotFoundException::new);
        return reviewRepository.findReviewsByMovie(movie).stream().map(ReviewDto::new).collect(Collectors.toList());
    }
}
