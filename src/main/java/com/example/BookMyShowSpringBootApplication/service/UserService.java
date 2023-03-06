package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.DeleteResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUser();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    DeleteResponseDto deleteUser(Long id);
}
