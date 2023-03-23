package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.BookingDto;
import com.example.BookMyShowSpringBootApplication.dto.BookingResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/bookings")
    List<BookingResponseDto> getAllBookings(Principal principal) {
        return bookingService.getAllBookings(principal.getName());
    }

    @GetMapping("/bookings/{bookingId}")
    BookingResponseDto getBooking(Principal principal, @PathVariable Long bookingId) {
        return bookingService.getBooking(principal.getName(), bookingId);
    }

    @PostMapping("/movies/{movieId}/shows/{showId}/book")
    ResponseDto bookShow(Principal principal, @PathVariable Long movieId, @PathVariable Long showId,
                         @Valid @RequestBody BookingDto bookingDto) {
        return bookingService.bookShow(principal.getName(), movieId, showId, bookingDto);
    }

    @DeleteMapping("/bookings/{bookingId}")
    ResponseDto cancelBooking(Principal principal, @PathVariable Long bookingId) {
        return bookingService.cancelBooking(principal.getName(), bookingId);
    }
}