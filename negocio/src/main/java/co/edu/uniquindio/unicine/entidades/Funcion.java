package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Funcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private Float precio;

    @ManyToOne
    private Horario horario;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Pelicula pelicula;

    @ToString.Exclude
    @OneToMany(mappedBy = "funcion")
    private List<Compra> compras;

    @Builder
    public Funcion(Float precio, Horario horario, Sala sala, Pelicula pelicula) {
        this.precio = precio;
        this.horario = horario;
        this.sala = sala;
        this.pelicula = pelicula;
    }
}
