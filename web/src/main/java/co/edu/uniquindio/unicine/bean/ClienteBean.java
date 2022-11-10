package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
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
public class ClienteBean implements Serializable {
    @Getter
    @Setter
    private Cliente cliente;

    @Getter
    @Setter
    private String confirmacionPassword;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
    }

    public void registrarCliente() {

        try {
            if (cliente.getPassword().equals(confirmacionPassword)) {
                clienteServicio.registrarCliente(cliente);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso!!");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "las contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

}

