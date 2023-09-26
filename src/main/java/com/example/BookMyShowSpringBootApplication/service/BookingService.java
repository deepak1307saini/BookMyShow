package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.BookingDto;
import com.example.BookMyShowSpringBootApplication.dto.BookingResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Booking;

import java.util.List;

public interface BookingService {
    List<BookingResponseDto> getAllBookings(String email);

    BookingResponseDto getBooking(String email, Long bookingId);

    ResponseDto bookShow(String email, Long movieId, Long showId, BookingDto bookingDto);

    ResponseDto cancelBooking(String email, Long bookingId);
}
