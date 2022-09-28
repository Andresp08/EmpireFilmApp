package com.co.movieApp.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
@Entity
public class Pelicula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpelicula")
    private Integer id;
    
    @NotBlank
    private String titulo;
    
    @NotBlank
    private String sinopsis;
    
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;
    
    @NotBlank
    private String youtubeTrailerId;
    
    private String rutaPortada;
    
    @NotEmpty
    //realiza la carga de la lista solo cuando se necesita
    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(name = "generopelicula", 
        joinColumns = @JoinColumn(name = "idpelicula"), 
        inverseJoinColumns = @JoinColumn(name = "idgenero"))
    private List<Genero> generos;
    
    //propiedad que no se almacena en la BD, si no que es temporal
    @Transient
    private MultipartFile portada;
    
}
