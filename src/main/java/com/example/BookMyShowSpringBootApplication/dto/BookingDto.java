/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookingDto {

	@NotEmpty
	List<String> seatNumbers;

}