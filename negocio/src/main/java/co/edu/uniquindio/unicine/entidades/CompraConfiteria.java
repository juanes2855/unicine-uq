package co.edu.uniquindio.unicine.entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CompraConfiteria implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;


    private Float precios;


    private Integer unidades;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Confiteria confiteria;
}
