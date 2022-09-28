package com.co.movieApp.controller;

import com.co.movieApp.model.Genero;
import com.co.movieApp.model.Pelicula;
import com.co.movieApp.repository.GeneroRepository;
import com.co.movieApp.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    //get movies
//    @GetMapping("")
//    public ModelAndView index(@PageableDefault(sort = "titulo", size = 5) Pageable pageable){
//        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
//        
//        return new ModelAndView("peliculas").addObject("peliculas", peliculas);
//    }
//    
//    @GetMapping("/peliculas/nuevo")
//    public ModelAndView nuevaPelicula(){
//        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
//        
//        return new ModelAndView("admin/nueva-pelicula")
//            .addObject("pelicula", new Pelicula())
//            .addObject("generos", generos);
//    }
    @GetMapping("")
    ModelAndView index(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);

        return new ModelAndView("admin/index")
                .addObject("peliculas", peliculas);
    }

    @GetMapping("/peliculas/nuevo")
    ModelAndView nuevaPelicula() {
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/nueva-pelicula")
                .addObject("pelicula", new Pelicula())
                .addObject("generos", generos);
    }
}
