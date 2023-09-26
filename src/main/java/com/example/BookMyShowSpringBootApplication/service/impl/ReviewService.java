package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ReviewDto;
import com.example.BookMyShowSpringBootApplication.dto.ReviewResponseDto;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Data
public class ReviewService {
    private final static String REVIEW_SYSTEM_URL = "http://localhost:8082/movies";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;


    public List<ReviewResponseDto> getAllReviews(long movieId) {
        String url = REVIEW_SYSTEM_URL + "/" + movieId + "/reviews";
        ResponseEntity<ReviewResponseDto[]> response = restTemplate.getForEntity(url, ReviewResponseDto[].class);
        if (response.getBody() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(response.getBody());
    }

    public ReviewResponseDto getReview(long movieId, String email) {
        String url = REVIEW_SYSTEM_URL + "/" + movieId + "/reviews" + "/" + email;
        ResponseEntity<ReviewResponseDto> response = restTemplate.getForEntity(url, ReviewResponseDto.class);
        return response.getBody();
    }

    public ResponseDto addReview(long movieId, ReviewDto reviewDto) {
        if (!userRepository.existsByEmail(reviewDto.getUserEmail())){
            return new ResponseDto(false,"User id not exist");
        }
        String url = REVIEW_SYSTEM_URL + "/" + movieId + "/reviews";
        ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, reviewDto, ResponseDto.class);
        return response.getBody();
    }

    public ResponseDto editReview(long movieId, ReviewDto reviewDto) {
        String url = REVIEW_SYSTEM_URL + "/" + movieId + "/reviews";
        ResponseEntity<ResponseDto> response = restTemplate.exchange(url,
                HttpMethod.PUT,
                new HttpEntity<>(reviewDto),
                ResponseDto.class);
        return response.getBody();
    }

    public ResponseDto deleteReview(long movieId, String email) {
        String url = REVIEW_SYSTEM_URL + "/" + movieId + "/reviews";
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserEmail(email);
        ResponseEntity<ResponseDto> response = restTemplate.exchange(url,
                HttpMethod.DELETE,
                new HttpEntity<>(reviewDto),
                ResponseDto.class);
        return response.getBody();
    }
}


