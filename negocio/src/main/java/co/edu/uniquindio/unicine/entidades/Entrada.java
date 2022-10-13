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
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private Float precio;

    private Integer fila;

    private Integer columna;

    @ManyToOne
    private Compra compra;

    @Builder
    public Entrada(Float precio, Integer fila, Integer columna) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
    }
}
