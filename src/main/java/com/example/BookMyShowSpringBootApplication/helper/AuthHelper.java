package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.dto.LoginRequestDto;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.exception.DuplicateRecordException;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import com.example.BookMyShowSpringBootApplication.service.EmailService;
import com.example.BookMyShowSpringBootApplication.service.UserService;
import com.example.BookMyShowSpringBootApplication.utility.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Component
public class AuthHelper {

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

    public void addUser(User user, User user1){
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

    }

    public void setCookies(HttpServletRequest req, LoginRequestDto loginDto){
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }
    public User userByEmail(String email){
        return userService.getUserByEmail(email);
    }

}
