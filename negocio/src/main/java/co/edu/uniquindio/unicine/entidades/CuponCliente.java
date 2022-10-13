package co.edu.uniquindio.unicine.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CuponCliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    private Cliente cedula_cliente;

    @ManyToOne
    private Cupon codigo_cupon;


    @OneToOne(mappedBy = "cuponCliente")
    private Compra compra;

    @Builder
    public CuponCliente(Boolean estado, Cliente cedula_cliente, Cupon codigo_cupon) {
        this.estado = estado;
        this.cedula_cliente = cedula_cliente;
        this.codigo_cupon = codigo_cupon;
    }
}
