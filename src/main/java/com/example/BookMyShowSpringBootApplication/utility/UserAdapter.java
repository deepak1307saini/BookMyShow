/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.utility;

import com.example.BookMyShowSpringBootApplication.dto.UserDto;
import com.example.BookMyShowSpringBootApplication.entity.User;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Random;

@UtilityClass
public class UserAdapter {

	public static User toEntity(UserDto userDto,String password) {
		Random rand = new Random();
		Long OTP = (long) (rand.nextInt(9000) + 1000);
		return User.builder()
				.username(userDto.getUsername())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(password)
				.activeStatus(false)
				.otp(OTP)
				.localDateTime(LocalDateTime.now())
				.build();

	}

	public static void updateUser(User oldUser,User newUser){
		oldUser.setName(newUser.getName());
		oldUser.setLocalDateTime(newUser.getLocalDateTime());
		oldUser.setPassword(newUser.getPassword());
		oldUser.setUsername(newUser.getUsername());
		oldUser.setOtp(newUser.getOtp());
	}

}