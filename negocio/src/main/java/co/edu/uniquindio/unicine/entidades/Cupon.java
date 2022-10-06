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

    @Positive
    @Column(nullable = false)
    private Float descuento;

    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    @Column(nullable = false)
    private Boolean estado;


    @OneToMany(mappedBy = "codigo_cupon")
    private List<CuponCliente> cuponClientes;

    @Builder

    public Cupon(Float descuento, LocalDateTime fechaVencimiento, Boolean estado) {
        this.descuento = descuento;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }
}
