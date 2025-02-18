package com.hande.chemical_database.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

/*
 * 18/02/2025
 * handebarkan
 */
@Builder
@Data
public class ChemicalDTO {
    @NotBlank
    @NotNull
    private String name;
    private String CASNo;
    private String LotNo;
    private String producer;
    @NotBlank
    @NotNull
    private String storage;
    private Boolean toxicState;
    private String responsible;
    private LocalDate orderDate;
    private String weight;
}
