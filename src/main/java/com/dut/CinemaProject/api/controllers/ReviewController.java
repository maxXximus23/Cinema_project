package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dao.domain.Review;
import com.dut.CinemaProject.services.interfaces.IReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("review")
public class ReviewController {

    private final IReviewService reviewService;
    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id){
        try {
            var review = reviewService.getById(id);
            return review.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        }
        catch(ArithmeticException ae){
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addReview(@RequestBody Review review, UriComponentsBuilder uriComponentsBuilder){
        try{
            reviewService.createReview(review);
            UriComponents uriComponents =
                    uriComponentsBuilder.path("/review/{id}").buildAndExpand(review.getId());
            var location = uriComponents.toUri();
            return ResponseEntity.created(location).build();
        }
        catch(ArithmeticException ae){
            return ResponseEntity.status(500).build();
        }
    }
}
