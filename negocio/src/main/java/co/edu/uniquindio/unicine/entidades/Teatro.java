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
public class Teatro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    private String direccion;

    private String telefono;


    @ManyToOne
    private Ciudad ciudad;

    @ManyToOne
    private AdministradorTeatro administradorTeatro;

    @OneToMany(mappedBy = "teatro")
    private List<Sala> salas;
}
