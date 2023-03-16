package com.example.BookMyShowSpringBootApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private String userEmail;
    private String comment;
    private Integer movieRating;

}
