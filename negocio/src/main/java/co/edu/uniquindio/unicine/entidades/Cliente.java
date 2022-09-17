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

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false, length = 200)
    private String urlFoto;

    @ElementCollection
    private List<String> telefonos;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Cupon> cupones;

    @Builder

    public Cliente(Integer cedula, String nombre,String correo,String password, String urlFoto, List<String> telefonos) {
        super(cedula,nombre,correo,password);
        this.urlFoto = urlFoto;
        this.estado = false;
        this.telefonos = telefonos;
    }
}
