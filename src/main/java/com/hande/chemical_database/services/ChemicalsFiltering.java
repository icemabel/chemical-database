package com.hande.chemical_database.services;

import com.hande.chemical_database.entities.Chemicals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 17/02/2025
 * handebarkan
 */
public interface ChemicalsFiltering {
    List<Chemicals> searchByName(String name);

    List<Chemicals> searchByCASNo(String CASNo);

    PageRequest buildPageRequest(Integer pageNumber, Integer pageSize);

    Page<Chemicals> listChemicalsByName(Chemicals name, Pageable pageable);
    Page<Chemicals> listChemicalsByStorage(Chemicals storage, Pageable pageable);
    Page<Chemicals> listChemicalsByOwner(Chemicals reponsible, Pageable pageable);
    Page<Chemicals> listChemicalsByOrderDate(Chemicals orderDate, Pageable pageable);
    Page<Chemicals> showToxicChemicals(Chemicals toxicChemicals, Pageable pageable);
}
