package com.hande.chemical_database.services;

import com.hande.chemical_database.exceptions.ResourceNotFoundException;
import com.hande.chemical_database.entities.Chemicals;
import com.hande.chemical_database.repositories.ChemicalRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * 17/02/2025
 * handebarkan
 */

//Service for CRUD
@Service
@Slf4j
@RequiredArgsConstructor
public class ChemicalServiceImpl implements ChemicalService {

    private final ChemicalRepo chemicalRepo;

    @Override
    public Chemicals createChemicals(Chemicals chemical) {
        return chemicalRepo.save(chemical);
    }

    @Override
    public Chemicals updateChemicals(Chemicals chemical) {

        Optional<Chemicals> chemicalDb = chemicalRepo.findById(chemical.getId());
        if(chemicalDb.isPresent()) {
            Chemicals chemicalUpdate = chemicalDb.get();
            chemicalUpdate.setName(chemical.getName());
            chemicalUpdate.setCASNo(chemical.getCASNo());
            chemicalUpdate.setLotNo(chemical.getLotNo());
            chemicalUpdate.setProducer(chemical.getProducer());
            chemicalUpdate.setStorage(chemical.getStorage());
            chemicalUpdate.setOrderDate(chemical.getOrderDate());
            chemicalUpdate.setOrderDate(chemical.getOrderDate());
            chemicalRepo.save(chemicalUpdate);
            return chemicalUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + chemical);
        }
    }

    @Override
    public List<Chemicals> getAllChemicals() {

        return chemicalRepo.findAll();
    }

    @Override
    public Chemicals getChemicalById(Long id) {

        Optional<Chemicals> chemicalsDb = chemicalRepo.findById(id);
        if(chemicalsDb.isPresent()) {
            return chemicalsDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }

    @Override
    public boolean deleteChemical(Long id) {
        Optional<Chemicals> chemicalsDb = chemicalRepo.findById(id);
        if(chemicalsDb.isPresent()) {
            chemicalRepo.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }

    }
}
