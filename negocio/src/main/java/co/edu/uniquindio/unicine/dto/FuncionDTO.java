package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.Horario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FuncionDTO {

    private String nombrePelicula;
    private String estadoPelicula;
    private String urlImagenPelicula;
    private Integer codigoSala;
    private String direccionTeatro;
    private String nombreCiudad;
    private Horario horario;


}
