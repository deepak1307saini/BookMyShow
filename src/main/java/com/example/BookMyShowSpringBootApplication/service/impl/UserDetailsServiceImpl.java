package com.example.BookMyShowSpringBootApplication.service.impl;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.entity.UserDetailsImpl;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository
                .findByEmail(email);
        if(user!=null){
            return new UserDetailsImpl(user);
        }
        else
            throw new UsernameNotFoundException("username not found " + email);
    }
}
