package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String nombre;

    private Float precio;

    private String urlImagen;

    @ToString.Exclude
    @OneToMany(mappedBy = "confiteria")
    private List<CompraConfiteria> compraConfiterias;

    @Builder
    public Confiteria(String nombre, Float precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }
}
