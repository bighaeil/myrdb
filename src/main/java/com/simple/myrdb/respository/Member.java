package com.simple.myrdb.respository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private LocalDate birthday;

}
