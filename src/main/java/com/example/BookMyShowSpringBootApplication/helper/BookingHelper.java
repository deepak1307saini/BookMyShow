package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.entity.Show;
import com.example.BookMyShowSpringBootApplication.entity.ShowSeat;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;
import com.example.BookMyShowSpringBootApplication.exception.NotFoundException;
import com.example.BookMyShowSpringBootApplication.repository.BookingRepository;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookingHelper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ShowHelper showHelper;

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public void checkShow(Long movieId, Long showId) {
        showHelper.checkShow(movieId, showId);
    }

    public Show getShow(Long showId) {
        return showHelper.getShow(showId);
    }

    public void checkBooking(String email, Long bookingId) {
        User user = getUser(email);
        if (!bookingRepository.findByIdAndUser(bookingId, user).isPresent())
            throw new NotFoundException("invalid booking id");
    }


    public void checkSeats(Show show, List<String> seatNos) {
        Set<String> allShowSeatNos = show.getShowSeats()
                .stream()
                .map(showSeat -> showSeat.getCinemaHallSeat().getSeatNo())
                .collect(Collectors.toSet());

        List<ShowSeat> allAvailableSeats=show.getShowSeats().stream().filter(showSeat -> showSeat.getSeatStatus().equals(SeatStatus.Available)).collect(Collectors.toList());
        List<String> availableSeats=new ArrayList<String>();
        for (ShowSeat showSeat:allAvailableSeats) {
           availableSeats.add( showSeat.getCinemaHallSeat().getSeatNo());
        }

        List<String> invalidSeats = seatNos.stream()
                .filter(seatNo -> !allShowSeatNos.contains(seatNo))
                .collect(Collectors.toList());

        if (!invalidSeats.isEmpty())
            throw new IllegalArgumentException(String.format("invalid seats: %s , Choose from : %s", invalidSeats,availableSeats.toString()));
    }

    public void canBook(Long movieId, Long showId, List<String> seatNos) {
        checkShow(movieId, showId);
        Show show = getShow(showId);
        checkSeats(show, seatNos);

        Set<String> seatNoSet = new HashSet<>(seatNos);
        List<String> bookedSeats = show.getShowSeats()
                .stream()
                .filter(showSeat -> seatNoSet.contains(showSeat.getCinemaHallSeat().getSeatNo()))
                .filter(showSeat -> showSeat.getSeatStatus() == SeatStatus.Booked)
                .map(showSeat -> showSeat.getCinemaHallSeat().getSeatNo())
                .collect(Collectors.toList());

        if (!bookedSeats.isEmpty())
            throw new IllegalArgumentException(String.format("seats %s are already booked", bookedSeats));
    }

    public int calcTotalPrice(Show show, int noOfSeats) {
        int price = show.getShowSeats()
                .get(0).getPrice();
        return price * noOfSeats;
    }

    public void canCancel(String email, Long bookingId) {
        checkBooking(email, bookingId);
    }
}