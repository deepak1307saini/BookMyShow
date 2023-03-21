/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import java.util.Date;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class ShowDto {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date startDateTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date endDateTime;

    @Min(1)
    private Long movieId;

    @Positive
    private Integer price;
}