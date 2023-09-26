package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.ShowSeat;
import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;
import com.example.BookMyShowSpringBootApplication.enums.SeatType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ShowSeatDto {

    @NotBlank
    private String seatNo;

    @NotNull
    private SeatType seatType;

    private int price;

    @NotNull
    private SeatStatus seatStatus;

    public ShowSeatDto(ShowSeat showSeat) {
        this.seatNo = showSeat.getCinemaHallSeat().getSeatNo();
        this.seatType = showSeat.getCinemaHallSeat().getSeatType();
        this.seatStatus = showSeat.getSeatStatus();
        this.price = showSeat.getPrice();
    }

}
