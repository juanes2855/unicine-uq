package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Cliente extends Persona implements Serializable {

    @Column(length = 150, nullable = false, unique = true)
    private String email;
    @ElementCollection
    private List<String> telefonos;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "cliente")
    private  List<Prestamo> prestamos;



}
