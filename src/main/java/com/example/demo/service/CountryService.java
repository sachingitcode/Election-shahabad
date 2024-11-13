package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryService {

    private static final Logger logger = LogManager.getLogger(CountryService.class);

    @Autowired
    private CountryRepo countryRepository;

    public List<String> getAllTehsil() {
        return countryRepository.getDistinctTehsil();
    }

    public List<String> getBlockByTehsilName(String countryName) {
        return countryRepository.getDistinctBlockName();
    }

    public List<String> getBlockByTehsilName() {
        return countryRepository.getDistinctBlockName();
    }

    public Optional<Country> getById(Long id) {
        return countryRepository.findById(id);
    }

    public List<Country> getDetailsGroupByBlock(String blocks) {
        return getAllData().stream()
                .filter(p -> p.getBlock().equalsIgnoreCase(blocks))
                .toList();
    }


    public List<Country> getAllData() {
        return countryRepository.findAll();
    }

    public void save(Country employee) {
        countryRepository.save(employee);
    }
}