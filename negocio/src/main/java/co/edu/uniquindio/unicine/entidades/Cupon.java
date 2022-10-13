package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Float descuento;

    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    @ToString.Exclude
    @OneToMany(mappedBy = "codigo_cupon")
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cupon(String descripcion, Float descuento, LocalDateTime fechaVencimiento) {
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaVencimiento = fechaVencimiento;
    }
}
