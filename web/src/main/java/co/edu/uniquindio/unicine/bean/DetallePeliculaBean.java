package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {


    @Autowired
    private AdminServicio adminServicio;
    @Getter
    @Setter
    @Value("#{param['pelicula_id']}")
    private String peliculaCodigo;

    @Getter @Setter
    private List<LocalDate> fechas;

    @Getter @Setter
    private LocalDate diaSemanas;

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void init(){
        try {
            ciudades = adminServicio.listarCiudades();
            fechas = new ArrayList<>();
            llenarDias();
            if(peliculaCodigo != null && !peliculaCodigo.isEmpty()){
                pelicula= adminServicio.obtenerPelicula(Integer.parseInt(peliculaCodigo));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void llenarDias() {
        Long dia = Long.valueOf(1);
        diaSemanas = LocalDate.now();
        for (long i=1; i<=7; i++ ){
            fechas.add(diaSemanas);
            diaSemanas = LocalDate.now().plusDays(i);
        }
        System.out.println(fechas);
    }

    public void actulizarFunciones(){
        try {
            if(ciudad != null){

            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

}
