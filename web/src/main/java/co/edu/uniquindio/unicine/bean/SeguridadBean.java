package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private Ciudad ciudadSeleccionada;
    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private String tipoSession;

    @PostConstruct
    public void init(){
        autenticado = false;
    }
    public String iniciarSesionCliente(){

        if(!email.isEmpty() && !password.isEmpty()){
            try {
                persona =  clienteServicio.login(email, password);
                if(persona instanceof Cliente) {
                    tipoSession = "cliente";
                    autenticado=true;
                    return "/index?faces-redirect=true";
                }
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Los campos son obligatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);
        }
        return null;
    }

    public String iniciarSesionAdmins(){

        if(!email.isEmpty() && !password.isEmpty()){
            try {
                persona =  adminServicio.login(email, password);

                if (persona == null) {
                    persona =  adminTeatroServicio.login(email, password);
                    tipoSession= "admin_teatro";
                }else{
                    tipoSession= "admin";
                }
                autenticado = true;

                return "/index_admin?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Los campos son obligatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);
        }
        return null;
    }
    public String cerrarSesion() {
        String tipo = tipoSession;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        if (tipo.equals("cliente")){
            return "/index?faces-redirect=true";
        }

        return "/index_admin?faces-redirect=true";

    }

    public void seleccionarCiudad(Ciudad ciudad){
        this.ciudadSeleccionada = ciudad;
    }


}
