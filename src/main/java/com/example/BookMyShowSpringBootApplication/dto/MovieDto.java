/**
 *
 */
package com.example.BookMyShowSpringBootApplication.dto;


import java.util.Date;
import java.util.List;

import com.example.BookMyShowSpringBootApplication.enums.CertificateType;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
    private Language language;

    @NotBlank
    private CertificateType certificateType;

    @NotBlank
    private Date releaseDate;

    @NotBlank
    private Genre genre;

    @NotEmpty
    private List<String> actorNames;

}