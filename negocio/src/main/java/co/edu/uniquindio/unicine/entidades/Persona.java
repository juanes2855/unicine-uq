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
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Integer Cedula;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false, unique = true)
    @Email
    private String correo;


    @Column(length = 150, nullable = false)
    private String password;

    public Persona(Integer cedula, String nombre, String correo, String password) {
        Cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public Persona(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
}
