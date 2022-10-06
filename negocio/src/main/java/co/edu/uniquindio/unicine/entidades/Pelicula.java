package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    //Resolver si es lista de sapos
    @Column(nullable = false)
    private Genero genero;

}
