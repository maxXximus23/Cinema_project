package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dao.domain.Review;
import com.dut.CinemaProject.dto.Review.NewReview;
import com.dut.CinemaProject.services.interfaces.IReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
@AllArgsConstructor
public class ReviewController {

    private final IReviewService reviewService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Review getById(@PathVariable Long id){
        return reviewService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addReview(@RequestBody NewReview newReview){
        return reviewService.createReview(newReview);
    }
}
