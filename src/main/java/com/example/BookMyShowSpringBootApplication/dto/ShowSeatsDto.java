/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;
import com.example.BookMyShowSpringBootApplication.enums.SeatType;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class ShowSeatsDto {

	private String seatNo;
	private SeatType seatType;
	private SeatStatus seatStatus;
	private int price;
//
//	public ShowSeatDTO(ShowSeat showSeat) {
//		this.seatNo = showSeat.getAudiSeat().getSeatNo();
//		this.seatType = showSeat.getAudiSeat().getSeatType();
//		this.seatStatus = showSeat.getSeatStatus();
//		this.price = showSeat.getPrice();
//	}
}