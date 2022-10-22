package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private String urlTrailer;

    @Column(nullable = false)
    private String urlImagen;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String reparto;

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genero> generos;

    @Builder
    public Pelicula(String nombre,String estado, String sinopsis, String urlTrailer, String urlImagen, String reparto, List<Genero> generos) {
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.urlTrailer = urlTrailer;
        this.urlImagen = urlImagen;
        this.reparto = reparto;
        this.generos = generos;
        this.estado = estado;
        generos = new ArrayList<Genero>();
        funciones = new ArrayList<Funcion>();

    }
}
