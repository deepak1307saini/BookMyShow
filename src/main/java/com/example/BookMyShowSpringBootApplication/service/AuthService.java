package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.*;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    public ResponseDto signUp(UserDto userDto);

    public ResponseDto verify(OtpDTO otpDTO);

    public ResponseDto login(HttpServletRequest req,LoginRequestDto loginDto);


    public ResponseDto changePassword(PasswordDto passwordDto);

    public ResponseDto forgetPassword(String email, String applicationUrl);

    public ResponseDto verify(VerificationRequestDto verificationRequestDto, String token);


}
