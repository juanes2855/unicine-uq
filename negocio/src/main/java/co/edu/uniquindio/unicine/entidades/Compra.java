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
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Positive
    @Column(nullable = false)
    private Float valorTotal;


    @OneToOne
    private CuponCliente cuponCliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Funcion funcion;

    @OneToMany(mappedBy = "compra")
    private List<Entrada> entradas;

    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> compraConfiterias;

    @Builder
    public Compra(MedioPago medioPago, Cliente cliente, Funcion funcion) {
        this.medioPago = medioPago;
        this.fecha = LocalDateTime.now();
        this.cliente = cliente;
        this.funcion = funcion;
    }
}
