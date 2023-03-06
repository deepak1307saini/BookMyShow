package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.CinemaDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import com.example.BookMyShowSpringBootApplication.service.CinemaService;
import com.example.BookMyShowSpringBootApplication.service.impl.CinemaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    CinemaServiceImpl cinemaService;

    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{cinemaId}")
    public Cinema getCinema(@PathVariable("cinemaId") Long cinemaId) {
        return cinemaService.getCinema(cinemaId);
    }

    @PostMapping
    public ResponseDto addCinema(@Valid @RequestBody CinemaDto cinemaDto) {
        return cinemaService.addCinema(cinemaDto);
    }

    @PutMapping("/{cinemaId}")
    public ResponseDto updateCinema(@PathVariable Long cinemaId, @Valid @RequestBody CinemaDto cinemaDto) {
        return cinemaService.updateCinema(cinemaId, cinemaDto);
    }

    @DeleteMapping("/{cinemaId}")
    public ResponseDto deleteCinema(@PathVariable Long cinemaId) {
        return cinemaService.deleteCinema(cinemaId);
    }
}