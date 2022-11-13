package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;

import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class TeatroBean implements Serializable {


    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private List<Teatro> teatrosSeleccionados;

    private Boolean editar;

    @PostConstruct
    public void init(){
        teatro = new Teatro();
        editar=false;
        ciudades = adminServicio.listarCiudades();
        teatrosSeleccionados = new ArrayList<>();
        teatros = adminTeatroServicio.listarTeatros();
    }

    public void crearTeatro(){
        try {
            if (!editar) {
                AdministradorTeatro administradorTeatro = adminServicio.obtenerAdministradorTeatro(1075954);

                teatro.setAdministradorTeatro(administradorTeatro);
                Teatro registro = adminTeatroServicio.crearTeatro(teatro);
                teatros.add(registro);
                teatro = new Teatro();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminTeatroServicio.actualizarTeatro(teatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }

    }

    public void eliminarTeatros(){

            try {
                for (Teatro t: teatrosSeleccionados) {
                    adminTeatroServicio.eliminarTeatro(t.getCodigo());
                    teatros.remove(t);
                }
                teatrosSeleccionados.clear();
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);;
            }

    }

    public String getTextoBtnBorrar(){
        if (teatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return teatrosSeleccionados.size()==1?  "Borrar 1 elemento" : "Borrar "+teatrosSeleccionados.size()+ " Elementos";
        }
    }

    public String getMensajeCrear(){
       return editar ? "Editar Teatro" : "Crear Teatro";
    }
    public void seleccionarTeatro(Teatro teatroSeleccionado){
        this.teatro=teatroSeleccionado;
        editar=true;
    }
    public void crearTeatroDialogo(){
        this.teatro= new Teatro();
        editar=false;

    }
}
