package com.example.BookMyShowSpringBootApplication.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    private String username;

    @Length(min = 3)
    private String name;

    @Email
    private String email;
    private String password;
    private String token;
    private Long otp;
    private Boolean activeStatus;
    private LocalDateTime localDateTime;


}
