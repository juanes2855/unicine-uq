package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {


    @Getter @Setter
    private Pelicula pelicula;

    @Autowired
    private AdminServicio adminServicio;

    @PostConstruct
    public void init(){
        pelicula = new Pelicula();
    }

    public String crearPelicula(){
        try {
            pelicula.setEstado(EstadoPelicula.CARTELERA);

            adminServicio.crearPelicula(pelicula);

            return "/admin/pelicula_creada?faces-redirect=true";

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

        }
        return "";
    }

}
