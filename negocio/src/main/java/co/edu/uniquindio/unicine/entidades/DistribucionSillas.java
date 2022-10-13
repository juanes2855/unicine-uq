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
public class DistribucionSillas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private Integer totalSillas;

    //Revisar si son listas
    private Integer filas;

    private Integer Columnas;
    // CONVERTIRLO EN MATRIZ
    private String esquema;

    @ToString.Exclude
    @OneToMany(mappedBy = "distribucionSillas")
    private List<Sala> salas;

    @Builder
    public DistribucionSillas(Integer filas, Integer columnas) {
        this.filas = filas;
        Columnas = columnas;
    }
}
