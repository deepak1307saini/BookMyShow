package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;

import java.util.List;

public interface MovieService {
    Movie addMovie(MovieDto movieDto);

    List<Movie> getMovies();

    List<Movie> getByActorName(String actorName);

    List<Movie> getByGenre(Genre genre);

    Movie getMovie(Long movieId);

    ResponseDto updateMovie(Long movieId, MovieDto movieRequestDTO);

    ResponseDto deleteMovie(Long movieId);

    List<Movie> getByMovieName(String partialMovieName);

    List<Movie> getByLanguage(Language language);
}
