package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncionDTO;
import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDate;
import java.util.List;

public interface ClienteServicio {


    Cliente obtenerCliente(Integer cedula) throws Exception;
    Cliente login(String correo , String password) throws Exception;

    Cliente registrarCliente(Cliente cliente) throws Exception;

    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Integer cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarHistorial(Integer cedulaCliente) throws Exception;

    Compra hacerCompra(Compra compra) throws Exception;

    void activarCliente(String correo, String fecha)throws Exception;

    boolean redimirCupon(Cliente cliente,Compra compra,Integer codigoCupon) throws Exception;

    List<Pelicula> buscarPelicula (String nombre);

    List<Pelicula> listarPorEstadoCiudad(EstadoPelicula estadoPelicula, Integer codigoCiudad) throws Exception;
    List<Pelicula> listarPorEstado(EstadoPelicula estadoPelicula) throws Exception;

    List<Confiteria> buscarConfiteria(String nombre);

    Entrada comprarEntradas (Entrada entrada);

    List<PeliculaFuncionDTO> buscarPeliculasConDTO(String nombre);

    boolean cambiarPassword(String correo) throws Exception;
    void enviarCorreo(Cliente cliente);

    void validarEstadoCuenta(Cliente cliente, String codigo) throws Exception;

    void enviarCorreoDetalleCompra(Compra compra);
    void enviarCorreoContraNueva(Cliente cliente);

    Compra hacerCompra(Cliente cliente, Funcion funcion, MedioPago medioPagoSeleccionado, List<CompraConfiteria> lista, Integer codigoCupon);

    List<Teatro> listarTeatrosPeliculaDia(Integer codigoCiudad, Integer codigoPelicula, LocalDate fecha);

    List<Funcion> listarFuncionesPeliculaDia(Integer codigoCiudad, Integer codigoPelicula, LocalDate fecha);

    Compra obtenerCompra(Integer codigo)throws Exception;
}
