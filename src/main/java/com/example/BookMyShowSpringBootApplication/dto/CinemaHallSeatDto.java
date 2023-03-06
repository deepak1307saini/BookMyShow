/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.enums.SeatType;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class CinemaHallSeatDto {
	@NotBlank
	private String seatNo;
	@NotBlank
	private SeatType seatType;
}