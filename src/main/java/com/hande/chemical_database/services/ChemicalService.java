package com.hande.chemical_database.services;

import com.hande.chemical_database.entities.Chemicals;

import java.util.List;

/*
 * 17/02/2025
 * handebarkan
 */
public interface ChemicalService {

    Chemicals createChemicals(Chemicals chemical);

    Chemicals updateChemicals(Chemicals chemical);

    List<Chemicals> getAllChemicals();

    Chemicals getChemicalById(Long id);

    boolean deleteChemical(Long id);




}

