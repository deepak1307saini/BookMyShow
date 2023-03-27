package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.*;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.exception.DuplicateRecordException;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import com.example.BookMyShowSpringBootApplication.service.AuthService;
import com.example.BookMyShowSpringBootApplication.service.EmailService;
import com.example.BookMyShowSpringBootApplication.service.UserService;
import com.example.BookMyShowSpringBootApplication.utility.CurrentTimeDate;
import com.example.BookMyShowSpringBootApplication.utility.GetEmailDetailInstance;
import com.example.BookMyShowSpringBootApplication.utility.UserAdapter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.UUID;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@Data
public class AuthServiceImpl implements AuthService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseDto signUp(UserDto userDto) {
        User user1 = userService.getUserByEmail(userDto.getEmail());
        if (user1 != null && user1.getActiveStatus()) {
            return new ResponseDto(false, "User already exists");
        }

        User user= UserAdapter.toEntity(userDto,passwordEncoder.encode(userDto.getPassword()));

        //generate
        Random rand = new Random();
        Long OTP = (long) (rand.nextInt(9000) + 1000);
        user.setOtp(OTP);

        // send mail
        emailService.sendSimpleMail(GetEmailDetailInstance.getInstance(user.getEmail(), user.getOtp()));

        if (user1 != null) {
            UserAdapter.updateUser(user1,user);
            try{userRepository.save(user1);}
            catch (Exception e){
                throw new DuplicateRecordException(String.format("Key (username)=(%s) already exists",user1.getUsername()));
            }

        } else
            try{userRepository.save(user);}
            catch (Exception e){
                throw new DuplicateRecordException(String.format("Key (username)=(%s) already exists",user.getUsername()));
            }

        return new ResponseDto(true, String.format("OTP sent to email : %s", userDto.getEmail()));
    }

    @Override
    public ResponseDto verify(OtpDTO otpDTO) {
        User user = userRepository.findByUsername(otpDTO.getUsername());

        if (CurrentTimeDate.timeDiff(user.getLocalDateTime())) {
            return new ResponseDto(false, "Time out, resend otp");
        }
        if (!user.getOtp().equals(otpDTO.getOtp())) {
            return new ResponseDto(false, "Otp not matched");
        }

        user.setActiveStatus(true);
        userRepository.save(user);
        return new ResponseDto(true, "User Verified");
    }

    @Override
    public ResponseDto login(HttpServletRequest req,LoginRequestDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null && !user.getActiveStatus()) {
            return new ResponseDto(false, "email not registered!");
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new ResponseDto(false, "invalid credentials!");
        }

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return new ResponseDto(true, String.format("User name = %s ," +
                "Email = %s ," +
                "Name = %s", user.getUsername(), user.getEmail(), user.getName()));
    }

    @Override
    public ResponseDto changePassword(PasswordDto passwordDto) {
        User user = userRepository.findByEmail(passwordDto.getEmail());
        if (user == null) {
            return new ResponseDto(false, "email not registered!");
        }
        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            return new ResponseDto(false, "incorrect Credentials!");
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);
        return new ResponseDto(true, "password changed!");
    }

    @Override
    public ResponseDto forgetPassword(String email, String applicationUrl) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseDto(false, "email not registered!");
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);
        String url = applicationUrl + "/verify?token=" + token;
        return new ResponseDto(true, String.format("Email Verification : %s", url));
    }

    @Override
    public ResponseDto verify(VerificationRequestDto verificationRequestDto, String token) {
        User user = userRepository.findByEmail(verificationRequestDto.getEmail());
        if (user == null) {
            return new ResponseDto(false, "email not registered!");
        }
        if (!token.equals(user.getToken())) {
            return new ResponseDto(false, "token invalid!");
        }
        user.setPassword(passwordEncoder.encode(verificationRequestDto.getNewPassword()));
        userRepository.save(user);
        return new ResponseDto(true, "password changed successfully");


    }
}
