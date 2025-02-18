package com.hande.chemical_database.repositories;

import com.hande.chemical_database.entities.Chemicals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

/*
 * 17/02/2025
 * handebarkan
 */
public interface ChemicalRepo extends JpaRepository<Chemicals, Long> {
    //List<Chemicals> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);

    List<Chemicals> findByName(String name);

    List<Chemicals> findByCASNo(String casNo);

    @Query("SELECT c.chemicals FROM Chemicals c")
    List<String> findAllChemicals();

    Page<Chemicals> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Chemicals> findByStorageContainingIgnoreCase(String storage, Pageable pageable);
    Page<Chemicals> findByOwnerContainingIgnoreCase(String owner, Pageable pageable);
    Page<Chemicals> findByOrderDate(LocalDate orderDate, Pageable pageable);
    Page<Chemicals> findByToxic(boolean toxic, Pageable pageable);
}

