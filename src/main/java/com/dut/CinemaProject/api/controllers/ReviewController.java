package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Review.NewReview;
import com.dut.CinemaProject.dto.Review.ReviewDto;
import com.dut.CinemaProject.services.interfaces.IReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@AllArgsConstructor
public class ReviewController {

    private final IReviewService reviewService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto getById(@PathVariable Long id){
        return reviewService.getById(id);
    }

    @GetMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> getListOfMovieReviews(@PathVariable Long id){
        return reviewService.getReviewsByMovie(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addReview(@RequestBody NewReview newReview){
        return reviewService.createReview(newReview);
    }
}
