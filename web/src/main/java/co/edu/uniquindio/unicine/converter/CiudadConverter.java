package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.repo.CiudadRepo;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {

    @Autowired
    private AdminServicio adminServicio;


    @Override
    public Ciudad getAsObject(FacesContext context, UIComponent component, String value) {
        Ciudad ciudad = null;
        try {
            ciudad = adminServicio.obtenerCiudad(Integer.parseInt(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ciudad;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Ciudad value) {
        if(value != null){
            return value.getCodigo()+"";
        }
        return "";
    }


}