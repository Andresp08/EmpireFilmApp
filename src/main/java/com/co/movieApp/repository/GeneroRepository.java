package com.co.movieApp.repository;

import com.co.movieApp.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    
}
