package com.co.movieApp.controller;

import com.co.movieApp.model.Genero;
import com.co.movieApp.model.Pelicula;
import com.co.movieApp.repository.GeneroRepository;
import com.co.movieApp.repository.PeliculaRepository;
import com.co.movieApp.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("")
    public ModelAndView index(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);

        return new ModelAndView("admin/index")
                .addObject("peliculas", peliculas);
    }

    @GetMapping("/peliculas/nuevo")
    public ModelAndView nuevaPelicula() {
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/nueva-pelicula")
                .addObject("pelicula", new Pelicula())
                .addObject("generos", generos);
    }

    @PostMapping("/peliculas/nuevo")
    public ModelAndView crearPelicula(@Validated Pelicula pelicula, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || pelicula.getPortada().isEmpty()) {

            if (pelicula.getPortada().isEmpty()) {
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }

            List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/nueva-pelicula")
                    .addObject("pelicula", pelicula)
                    .addObject("generos", generos);
        }

        //almacenar el archivo y así mismo la ruta donde se almacena la imagen de la portada
        String rutaPortada = fileSystemStorageService.storage(pelicula.getPortada());
        pelicula.setRutaPortada(rutaPortada);

        peliculaRepository.save(pelicula);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/peliculas/{id}/editar")
    public ModelAndView editarPelicula(@PathVariable Integer id) {
        Pelicula pelicula = peliculaRepository.getOne(id);
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-pelicula")
                .addObject("pelicula", pelicula)
                .addObject("generos", generos);
    }

    @PostMapping("/peliculas/{id}/editar")
    public ModelAndView actualizarPelicula(@PathVariable Integer id, @Validated Pelicula pelicula,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/editar-pelicula")
                    .addObject("pelicula", pelicula)
                    .addObject("generos", generos);
        }

        Pelicula peliculaFromDB = peliculaRepository.getOne(id);
        peliculaFromDB.setTitulo(pelicula.getTitulo());
        peliculaFromDB.setSinopsis(pelicula.getSinopsis());
        peliculaFromDB.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaFromDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
        peliculaFromDB.setGeneros(pelicula.getGeneros());

        if (!pelicula.getPortada().isEmpty()) {
            fileSystemStorageService.delete(peliculaFromDB.getRutaPortada());
            String rutaPortada = fileSystemStorageService.storage(pelicula.getPortada());
            peliculaFromDB.setRutaPortada(rutaPortada);
        }
        peliculaRepository.save(peliculaFromDB);

        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/peliculas/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Integer id) {
        Pelicula pelicula = peliculaRepository.getOne(id);
        peliculaRepository.delete(pelicula);
        fileSystemStorageService.delete(pelicula.getRutaPortada());

        return "redirect:/admin";
    }
}
