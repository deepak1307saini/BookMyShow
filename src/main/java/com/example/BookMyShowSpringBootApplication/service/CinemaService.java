package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.CinemaDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> getAllCinemas();

    Cinema getCinema(Long cinemaId);

    ResponseDto addCinema(CinemaDto cinemaDto);

    ResponseDto updateCinema(Long cinemaId, CinemaDto cinemaDto);

    ResponseDto deleteCinema(Long cinemaId);
}
