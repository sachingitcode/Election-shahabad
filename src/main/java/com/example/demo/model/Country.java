package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "country_state_mapping")

public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tehsil, block, village, name, phone,caste;
}
