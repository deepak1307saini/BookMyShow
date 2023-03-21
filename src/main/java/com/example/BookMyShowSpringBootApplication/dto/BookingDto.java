/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import java.util.List;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookingDto {

	@NotEmpty
	List<String> seatNumbers;

}