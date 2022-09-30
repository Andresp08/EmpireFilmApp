package com.co.movieApp.controller;

import com.co.movieApp.model.Genero;
import com.co.movieApp.model.Pelicula;
import com.co.movieApp.repository.GeneroRepository;
import com.co.movieApp.repository.PeliculaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping("")
    public ModelAndView index() {

        //(Starter page=0; size Pages = 4)
        List<Pelicula> ultimasPeliculas = peliculaRepository.
                findAll(PageRequest.of(0, 4, Sort.by("FechaEstreno").descending())).toList();

        return new ModelAndView("index").addObject("ultimasPeliculas", ultimasPeliculas);
    }

    @GetMapping("/peliculas")
    public ModelAndView listaPeliculas(@PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
        return new ModelAndView("peliculas").addObject("peliculas", peliculas);

    }

    @GetMapping("/peliculas/{id}")
    public ModelAndView verDetallesPelicula(@PathVariable Integer id) {
        Pelicula pelicula = peliculaRepository.getOne(id);
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
        
        return new ModelAndView("detalles-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos);
    }

}
