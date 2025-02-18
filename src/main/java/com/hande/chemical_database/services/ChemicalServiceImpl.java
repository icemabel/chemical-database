package com.hande.chemical_database.services;

import com.hande.chemical_database.exceptions.ResourceNotFoundException;
import com.hande.chemical_database.entities.Chemicals;
import com.hande.chemical_database.mappers.ChemicalMapper;
import com.hande.chemical_database.models.ChemicalDTO;
import com.hande.chemical_database.repositories.ChemicalRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final ChemicalMapper chemicalMapper;

    @Override
    public ChemicalDTO createChemicals(ChemicalDTO chemical) {

        return chemicalMapper.chemicalToChemicalDTO(chemicalRepo.save(chemicalMapper.chemicalDTOToChemical(chemical)));
    }

    @Override
    public Optional<ChemicalDTO> updateChemicals(ChemicalDTO chemical) {

        Optional<ChemicalDTO> chemicalDb = chemicalRepo.findAllByNameIsLikeIgnoreCase(chemical.getName());
        if(chemicalDb.isPresent()) {
            ChemicalDTO chemicalUpdate = chemicalDb.get();
            chemicalUpdate.setName(chemical.getName());
            chemicalUpdate.setCASNo(chemical.getCASNo());
            chemicalUpdate.setLotNo(chemical.getLotNo());
            chemicalUpdate.setProducer(chemical.getProducer());
            chemicalUpdate.setStorage(chemical.getStorage());
            chemicalUpdate.setOrderDate(chemical.getOrderDate());
            ChemicalDTO updatedChemical = chemicalMapper.chemicalToChemicalDTO(
                    chemicalRepo.save(chemicalMapper.chemicalDTOToChemical(chemicalUpdate)));
            return Optional.of(updatedChemical);
        } else {
            throw new ResourceNotFoundException("Record not found with name : " + chemical);
        }
    }

    @Override
    public List<ChemicalDTO> getAllChemicals() {

        return chemicalRepo.findAll().stream()
                .map(chemicalMapper::chemicalToChemicalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChemicalDTO> getChemicalById(Long id) {

        return Optional.ofNullable(chemicalMapper.chemicalToChemicalDTO(chemicalRepo.findById(id)
                .orElse(null)));
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

    @Override
    public boolean deleteChemicalByName(String name) {
        if(chemicalRepo.existsByName(name)) {
            chemicalRepo.deleteByName(name);
            return true;
        } else {
            throw new ResourceNotFoundException("Record not found with name : " + name);
        }
    }
}
