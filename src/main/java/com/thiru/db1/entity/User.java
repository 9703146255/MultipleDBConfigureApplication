package com.thiru.db1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the ID
    private Long id;  // ID will be auto-generated

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    // Getters and Setters
}
