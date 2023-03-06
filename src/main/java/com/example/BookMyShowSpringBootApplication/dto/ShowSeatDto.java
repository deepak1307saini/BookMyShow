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

public class ShowSeatDto {

	private String seatNo;
	private SeatType seatType;
	private SeatStatus seatStatus;
	private int price;

}