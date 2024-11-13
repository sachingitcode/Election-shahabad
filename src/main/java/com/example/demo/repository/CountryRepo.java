package com.example.demo.repository;

import com.example.demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CountryRepo extends JpaRepository<Country, Long> {

    @Query(value = "select  distinct tehsil from  country_state_mapping ", nativeQuery = true)
    List<String> getDistinctTehsil();


    @Query(value = "select  distinct trim(block) from  country_state_mapping ", nativeQuery = true)
    List<String> getDistinctBlockName();




}
