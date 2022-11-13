package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Lob
    @Column(nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private String urlTrailer;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPelicula estado;
    @Lob
    @Column(nullable = false)
    private String reparto;

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genero> generos;

    @Builder
    public Pelicula(String nombre,EstadoPelicula estado, String sinopsis, String urlTrailer,  String reparto, List<Genero> generos) {
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.urlTrailer = urlTrailer;
        this.reparto = reparto;
        this.generos = generos;
        this.estado = estado;

    }

    public String getObtenerImagenPrincipal(){
        if(!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";

    }
}
