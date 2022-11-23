package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class GestionCuentaBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter
    @Setter
    @Value("#{param['p1']}")
    private String param1;

    @Getter @Setter
    @Value("#{param['p2']}")
    private String param2;

    @Getter @Setter
    private String  mensaje= "Verificando cuenta ..";

    @PostConstruct
    public void init(){
        if(param1 != null && !param1.isEmpty() && param2 != null && !param2.isEmpty()){
            try {
                clienteServicio.activarCliente(param1, param2);
                mensaje = "Cuenta Verificada";
            }catch (Exception e){
                throw new RuntimeException(e);
            }

        }
    }
}
