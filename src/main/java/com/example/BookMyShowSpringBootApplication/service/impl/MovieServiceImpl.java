/**
 *
 */
package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;
import com.example.BookMyShowSpringBootApplication.helper.MovieHelper;
import com.example.BookMyShowSpringBootApplication.repository.ActorRepository;
import com.example.BookMyShowSpringBootApplication.service.MovieService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.repository.MovieRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Data
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieHelper movieHelper;

    @Override
    public Movie addMovie(MovieDto movieDto) {
        movieHelper.canAdd(movieDto);
        Movie movie = new Movie();
        movieHelper.mapMovieRequestToMovie(movieDto,movie);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movieRepository.findAll()
                .forEach(movies::add);

        return movies;
    }

    @Override
    public List<Movie> getByActorName(String actorName) {
        Set<Movie> movies = new HashSet<>();

        actorRepository.findByNameContaining(actorName)
                .forEach(actor -> movies.addAll(actor.getMovies()));

        return new ArrayList<>(movies);
    }

    @Override
    public List<Movie> getByGenre(Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public Movie getMovie(Long movieId) {
        movieHelper.checkMovie(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        return movie;
    }

    @Override
    public ResponseDto updateMovie(Long movieId, MovieDto movieRequestDTO) {
        movieHelper.canUpdate(movieId);

        Movie movie = movieHelper.getMovie(movieId);
        movieHelper.mapMovieRequestToMovie(movieRequestDTO, movie);
        movieRepository.save(movie);
        return new ResponseDto(true, String.format("movie %s updated successfully", movie.getName()));
    }

    public List<Movie> getByMovieName(String partialMovieName) {
        return movieRepository.findByNameContaining(partialMovieName);
    }

    public List<Movie> getByLanguage(Language language) {
        return movieRepository.findByLanguage(language);
    }

    @Override
    public ResponseDto deleteMovie(Long movieId) {
        movieHelper.canDelete(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        movieRepository.delete(movie);
        return new ResponseDto(true, String.format("movie %s deleted successfully", movie.getName()));
    }

}