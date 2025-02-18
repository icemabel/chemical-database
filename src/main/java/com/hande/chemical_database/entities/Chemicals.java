package com.hande.chemical_database.entities;

/*
 * 17/02/2025
 * handebarkan
 */

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Chemicals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String CASNo;
    private String LotNo;
    private String producer;
    private String storage;
    @Column(nullable = true)
    private Boolean toxicState;
    private String responsible;
    private LocalDate orderDate;
    private String weight;
}
