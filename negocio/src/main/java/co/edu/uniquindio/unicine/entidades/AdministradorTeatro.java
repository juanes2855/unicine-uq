package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {

    @ToString.Exclude
    @OneToMany(mappedBy = "administradorTeatro")
    private List<Teatro> teatros;


    @Builder
    public AdministradorTeatro(Integer cedula, String nombre, String correo, String password) {
        super(cedula,nombre,correo, password);
        teatros = new ArrayList<Teatro>();
    }
}
