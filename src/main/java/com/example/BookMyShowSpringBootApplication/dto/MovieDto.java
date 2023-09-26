package com.example.BookMyShowSpringBootApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class MovieDto {
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String language;

    @NotBlank
    private String certificateType;

    @NotNull
    private Date releaseDate;

    @NotBlank
    private String genre;

    @NotEmpty
    private List<String> actorNames;

    public MovieDto(long movieId){
        this.id=movieId;
    }

}