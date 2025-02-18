package com.hande.chemical_database.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/*
 * 18/02/2025
 * handebarkan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String position;
    private int duration;

}
