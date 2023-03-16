package com.example.BookMyShowSpringBootApplication.controller;


import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ReviewDto;
import com.example.BookMyShowSpringBootApplication.dto.ReviewResponseDto;
import com.example.BookMyShowSpringBootApplication.service.impl.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies/{movieId}/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getAllReviews(@PathVariable int movieId) {
        List<ReviewResponseDto> reviewResponseDtoS = reviewService.getAllReviews(movieId);
        return new ResponseEntity<>(reviewResponseDtoS, HttpStatus.OK);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getReview(@PathVariable int movieId, @PathVariable String userEmail) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(movieId, userEmail);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addReview(@PathVariable int movieId,
                                       @Valid @RequestBody ReviewDto reviewDto) {
        ResponseDto responseDto = reviewService.addReview(movieId, reviewDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editReview(@PathVariable int movieId, @Valid @RequestBody ReviewDto reviewDto) {
        ResponseDto responseDto = reviewService.editReview(movieId, reviewDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userEmail}")
    public ResponseEntity<?> deleteReview(@PathVariable int movieId, @PathVariable String userEmail) {
        ResponseDto responseDto = reviewService.deleteReview(movieId, userEmail);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
