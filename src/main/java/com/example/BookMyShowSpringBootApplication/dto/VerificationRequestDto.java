package com.example.BookMyShowSpringBootApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequestDto {
    @Email
    private String email;
    private String newPassword;
}
