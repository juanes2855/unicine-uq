package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

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

    boolean redimirCupon(Integer codigoCupon) throws Exception;

    List<Pelicula> buscarPelicula (String nombre);

    List<Confiteria> buscarConfiteria(String nombre);

    Entrada comprarEntradas (Entrada entrada);

    boolean cambiarPassword(Integer cedula) throws Exception;
}
