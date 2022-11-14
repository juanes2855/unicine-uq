package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class CompraBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private List<Confiteria> confiterias;

    @Getter @Setter
    private ArrayList<CompraConfiteria> confiteriasFormulario;

    @Getter @Setter
    private ArrayList<Entrada> entradas;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private ArrayList<Integer> filas, columnas;

    @Getter @Setter
    private Float precioCompra;

    @Getter @Setter
    private String funcionCodigo;

    @Getter @Setter
    private List<MedioPago> medioPagos;

    @Getter @Setter
    private MedioPago medioPago;

    @PostConstruct
    public void init(){

        confiterias = adminServicio.listarConfiterias();
        confiteriasFormulario = new ArrayList<>();
        medioPagos = Arrays.asList(MedioPago.values());
        filas = new ArrayList<>();
        columnas = new ArrayList<>();
        precioCompra = 0f;

        confiterias.forEach(c -> {
            confiteriasFormulario.add(CompraConfiteria.builder().confiteria(c).precio(c.getPrecio()).unidades(0).build()); // revisar
        });

        try {
            cliente = clienteServicio.obtenerCliente(1);
             if (funcionCodigo != null && !funcionCodigo.isEmpty()){
                 funcion = adminTeatroServicio.obtenerFuncion(Integer.parseInt(funcionCodigo));
                 crearDistribucionSala();
             }
        }catch (Exception e){
            throw  new RuntimeException();
        }
    }

    private void crearDistribucionSala() {

    }
}
