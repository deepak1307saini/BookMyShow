package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.*;
import com.example.BookMyShowSpringBootApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseDto signUp(@RequestBody @Valid UserDto userDto) {
        return authService.signUp(userDto);
    }

    @PostMapping("/verifyRegistration")
    public ResponseDto verifyRegistration(@RequestBody OtpDTO otpDTO) {
        return authService.verify(otpDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req, @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authService.login(req,loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseDto changePassword(@RequestBody PasswordDto passwordDto) {
        return authService.changePassword(passwordDto);

    }

    @PostMapping("/forgetPassword")
    public ResponseDto forgetPassword(@RequestParam("email") String email, HttpServletRequest request) {
        return authService.forgetPassword(email, getApplicationUrl(request));
    }

    @PostMapping("/verify")
    public ResponseDto verify(@RequestBody VerificationRequestDto verificationRequestDto, @RequestParam("token") String token) {
        return authService.verify(verificationRequestDto, token);
    }

    public String getApplicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @GetMapping("/")
    public String getUser(Principal principal){
        return "Welcome " + principal.getName();
    }




}
