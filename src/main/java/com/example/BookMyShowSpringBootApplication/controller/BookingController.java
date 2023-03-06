package com.example.BookMyShowSpringBootApplication.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.BookMyShowSpringBootApplication.dto.BookingDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Booking;
import com.example.BookMyShowSpringBootApplication.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user/{userId}")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/bookings")
    List<Booking> getAllBookings(@PathVariable Long userId) {
        return bookingService.getAllBookings(userId);
    }

    @GetMapping("/bookings/{bookingId}")
    Booking getBooking(@PathVariable Long userId, @PathVariable Long bookingId) {
        return bookingService.getBooking(userId, bookingId);
    }

    @PostMapping("/movies/{movieId}/shows/{showId}/book")
    ResponseDto bookShow(@PathVariable Long userId, @PathVariable Long movieId, @PathVariable Long showId,
                         @Valid @RequestBody BookingDto bookingDto) {
        return bookingService.bookShow(userId, movieId, showId, bookingDto);
    }

    @DeleteMapping("/bookings/{bookingId}")
    ResponseDto cancelBooking(@PathVariable Long userId, @PathVariable Long bookingId) {
        return bookingService.cancelBooking(userId, bookingId);
    }
}