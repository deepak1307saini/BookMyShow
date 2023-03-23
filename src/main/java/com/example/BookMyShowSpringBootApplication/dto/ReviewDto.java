package com.example.BookMyShowSpringBootApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDto {

    @Email
    private String userEmail;
    @NotBlank
    private String comment;

    @Min(value = 1)
    @Max(value = 10)
    private int movieRating;
}
