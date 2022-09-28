
package com.co.movieApp.repository;

import com.co.movieApp.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{
    
}
