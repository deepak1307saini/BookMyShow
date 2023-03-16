package com.example.BookMyShowSpringBootApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDto {

    @NotEmpty
    private String userEmail;

    @NotEmpty
    private String comment;
    @Max(10)
    @Min(1)
    private Integer movieRating;
}
