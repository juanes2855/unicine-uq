package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Persona implements Serializable {
    @NotNull
    @Id
    @EqualsAndHashCode.Include
    private Integer cedula;

    @Length(max = 100)
    @Column(nullable = false , length = 100)
    private String nombre;

    @Length(max = 200)
    @NotNull
    @Column(length = 200, nullable = false, unique = true)
    @Email
    private String correo;

    @Length(max = 100)
    @ToString.Exclude
    @Column(length = 100, nullable = false)
    private String password;

    public Persona(Integer cedula, String nombre, String correo, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

}
