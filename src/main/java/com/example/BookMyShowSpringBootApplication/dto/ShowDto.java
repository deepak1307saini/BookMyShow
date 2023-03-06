/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class ShowDto {

	@NotNull @Future
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;

	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date startTime;

	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date endTime;

	@NotNull
	private Integer movieId;

	@NotNull @Positive
	private Integer price;
}