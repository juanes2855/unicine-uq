package co.edu.uniquindio.unicine.entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompraConfiteria implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private Float precio;
    @Getter @Setter
    private Integer unidades;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Confiteria confiteria;

    @Builder

    public CompraConfiteria(Float precio, Integer unidades, Compra compra, Confiteria confiteria) {
        this.precio = precio;
        this.unidades = unidades;
        this.compra = compra;
        this.confiteria = confiteria;
    }

    public void sumar(){
        unidades = unidades + 1;
    }
    public void restar(){
        unidades = unidades - 1;
    }

}
