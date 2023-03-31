package com.example.BookMyShowSpringBootApplication.repository;


import com.example.BookMyShowSpringBootApplication.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    List<Token> findAllByUserEmail(String email);

    Optional<Token> findByToken(String token);
}