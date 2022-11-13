package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdminServicio {

    Ciudad crearCiudad(Ciudad ciudad) throws Exception;
    Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception;

    List<Ciudad> listarCiudades();
    Pelicula crearPelicula(Pelicula pelicula) throws Exception;
    Pelicula actualizarPelicula(Pelicula pelicula) throws Exception;
    void eliminarPelicula(Integer codigoPelicula) throws Exception;
    List<Pelicula> listarPeliculas();
    Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception;



    Cupon crearCupon(Cupon cupon) throws Exception;
    Cupon actualizarCupon(Cupon cupon) throws Exception;
    void eliminarCupon(Integer codigoCupon) throws Exception;
    List<Cupon> listarCupones();
    Cupon obtenerCupon(Integer codigoCupon) throws Exception;

    Administrador login(String correo , String password) throws Exception;


    AdministradorTeatro registrarAdministradorTeatro(AdministradorTeatro cliente) throws Exception;
    AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro cliente) throws Exception;
    void eliminarAdministradorTeatro(Integer cedulaAdministradorTeatro) throws Exception;
    List<AdministradorTeatro> listarAdministradorTeatro();
    AdministradorTeatro obtenerAdministradorTeatro(Integer cedula) throws Exception;

    Confiteria crearConfiteria(Confiteria confiteria) throws Exception;
    Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception;
    void eliminarConfiteria(Integer codigoConfiteria) throws Exception;
    List<Confiteria> listarConfiterias();
    Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception;


    
}
