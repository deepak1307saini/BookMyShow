/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookingDto {

	List<String> seatNumbers;

}