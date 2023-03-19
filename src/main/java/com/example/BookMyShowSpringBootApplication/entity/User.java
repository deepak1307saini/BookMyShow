package com.example.BookMyShowSpringBootApplication.entity;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @NotEmpty
    private String username;

    @Length(min = 3)
    private String name;

    @Email @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String token;

    @NotEmpty
    private Long otp;
    private Boolean activeStatus;

    @NotEmpty
    private LocalDateTime localDateTime;
}
