package com.hande.chemical_database.services;

import com.hande.chemical_database.models.Chemicals;
import com.hande.chemical_database.repositories.ChemicalRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
 * 17/02/2025
 * handebarkan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ChemicalsFilteringImpl implements ChemicalsFiltering {

    private final ChemicalRepo chemicalRepo;
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public List<Chemicals> searchByName(String name) {

        return chemicalRepo.findByName(name);
    }

    @Override
    public List<Chemicals> searchByCASNo(String CASNo) {
        return chemicalRepo.findByCASNo(CASNo);
    }

    @Override
    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }
        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }
        Sort sort = Sort.by(Sort.Order.asc("name"));
        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

    @Override
    public Page<Chemicals> listChemicalsByName(Chemicals chemical, Pageable pageable) {
        String name = chemical.getName();
        // If no name is provided, return all chemicals.
        if (name == null || name.trim().isEmpty()) {
            return chemicalRepo.findAll(pageable);
        }
        return chemicalRepo.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Chemicals> listChemicalsByStorage(Chemicals chemical, Pageable pageable) {
        String storage = chemical.getStorage();
        if (storage == null || storage.trim().isEmpty()) {
            return chemicalRepo.findAll(pageable);
        }
        return chemicalRepo.findByStorageContainingIgnoreCase(storage, pageable);
    }

    @Override
    public Page<Chemicals> listChemicalsByOwner(Chemicals chemical, Pageable pageable) {
        String owner = chemical.getResponsible();
        if (owner == null || owner.trim().isEmpty()) {
            return chemicalRepo.findAll(pageable);
        }
        return chemicalRepo.findByOwnerContainingIgnoreCase(owner, pageable);
    }

    @Override
    public Page<Chemicals> listChemicalsByOrderDate(Chemicals chemical, Pageable pageable) {
        LocalDate orderDate = chemical.getOrderDate();
        if (orderDate == null) {
            return chemicalRepo.findAll(pageable);
        }
        return chemicalRepo.findByOrderDate(orderDate, pageable);
    }

    @Override
    public Page<Chemicals> showToxicChemicals(Chemicals chemical, Pageable pageable) {
        // Here, we assume that this method always shows toxic chemicals.
        // Alternatively, you could check chemical.isToxic() if you want to allow a flag.
        return chemicalRepo.findByToxic(true, pageable);
    }
}
