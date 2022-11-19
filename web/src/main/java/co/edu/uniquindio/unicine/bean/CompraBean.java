package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class CompraBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Value("#{param['d']}")
    private String diaSeleccionado;

    @Value("#{param['m']}")
    private String mesSeleccionado;

    @Value("#{param['a']}")
    private String anioSeleccionado;

    @Getter @Setter
    private LocalDateTime fechaSeleccionada;

    @Getter @Setter
    private List<Confiteria> confiterias;

    @Getter @Setter
    private ArrayList<CompraConfiteria> confiteriasFormulario;

    @Getter @Setter
    private ArrayList<Entrada> entradas;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private DistribucionSillas distribucionSillas;

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

    @Getter @Setter
    private MedioPago medioPagoSeleccionado;

    @Getter @Setter
    private Integer codigoCupon;

    @PostConstruct
    public void init(){

        confiterias = adminServicio.listarConfiterias();
        confiteriasFormulario = new ArrayList<>();
        medioPagos = Arrays.asList(MedioPago.values());
        filas = new ArrayList<>();
        columnas = new ArrayList<>();
        entradas = new ArrayList<>();
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
             if(diaSeleccionado != null && mesSeleccionado != null & anioSeleccionado != null){
                 String[] hora = funcion.getHorario().getHora().split(":");
                 fechaSeleccionada = LocalDateTime.of(Integer.parseInt(anioSeleccionado),Integer.parseInt(mesSeleccionado), Integer.parseInt(diaSeleccionado), Integer.parseInt(hora[0]),Integer.parseInt(hora[1]));
             }
        }catch (Exception e){
            throw  new RuntimeException();
        }
    }

    private void crearDistribucionSala() {
        distribucionSillas = funcion.getSala().getDistribucionSillas();
        String esquema = distribucionSillas.getEsquema();

        for(int i = 0; i < distribucionSillas.getFilas(); i++){
            filas.add(i);
        }
        for(int i = 0; i < distribucionSillas.getColumnas() ; i++){
            columnas.add(i);
        }

    }

    private void buscar(Integer fila,Integer col){

    }

    private void seleccionarSilla(Integer fila, Integer col){

    }

    private void restarUnidades(Integer cantidad){

    }

    private void sumarUnidades(Integer cantidad){

    }

    private String hacerCompra(){
        if(!entradas.isEmpty() && fechaSeleccionada!= null){
            try{
                List<CompraConfiteria> lista = confiteriasFormulario.stream().filter(c -> c.getUnidades() > 0).collect(Collectors.toList());

                Compra compra = clienteServicio.hacerCompra(cliente, funcion, medioPagoSeleccionado, lista, codigoCupon);
                FacesMessage fm;
                if (compra != null){
                    fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente");
                }else{
                    fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente");
                }
                FacesContext.getCurrentInstance().addMessage("msj_bean", fm);
                Thread.sleep(2000);
                return "/cliente/detalle_compra.xhtml?faces-redirect=true&amp;compra_id="+compra.getCodigo();

            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Se necesita seleccionar la silla");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
        return "";
    }
}
