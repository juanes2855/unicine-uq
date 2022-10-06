package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;


    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;

    @ManyToOne
    private Teatro teatro;

    @ManyToOne
    private DistribucionSillas distribucionSillas;



}
