package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Integer cedula;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false, unique = true)
    @Email
    private String correo;

    @ToString.Exclude
    @Column(length = 150, nullable = false)
    private String password;

    public Persona(Integer cedula, String nombre, String correo, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

}
