package com.hande.chemical_database.services;

import com.hande.chemical_database.models.Chemicals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

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

