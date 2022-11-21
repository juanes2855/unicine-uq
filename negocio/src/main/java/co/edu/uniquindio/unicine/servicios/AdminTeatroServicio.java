package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminTeatroServicio {

    Horario crearHorario(Horario horario) throws Exception;
    void eliminarHorario(Integer codigoHorario) throws Exception;
    List<Horario> listarHorarios();
    // Horario obtenerHorario(Integer codigoHorario) throws Exception;

    Sala crearSala(Sala sala) throws Exception;
    Sala actualizarSala(Sala sala) throws Exception;
    void eliminarSala(Integer codigoSala) throws Exception;
    List<Sala> listarSalas();
    Sala obtenerSala(Integer codigoSala) throws Exception;

    Funcion crearFuncion(Funcion funcion) throws Exception;
    Funcion actualizarFuncion(Funcion funcion) throws Exception;
    void eliminarFuncion(Integer codigoFuncion) throws Exception;
    List<Funcion> listarFunciones();
    List<Funcion> listarFuncionesCiudad(Integer codigoCiudad);

    List<Funcion> listarFuncionesTeatro(Integer codigoTeatro);
    Funcion obtenerFuncion(Integer codigoFuncion) throws Exception;

    Teatro crearTeatro(Teatro teatro) throws Exception;
    Teatro actualizarTeatro(Teatro teatro) throws Exception;
    void eliminarTeatro(Integer codigoTeatro) throws Exception;
    List<Teatro> listarTeatros();

    List<Teatro> listarTeatrosCiudad(Ciudad ciudad);
    Teatro obtenerTeatro(Integer codigoTeatro) throws Exception;

    AdministradorTeatro login(String correo , String password) throws Exception;

    boolean validarHora(String hora) throws Exception;

    boolean esHorarioValido(Horario horario) throws Exception;

    boolean validarRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    DistribucionSillas registrarDistribucionSilla(DistribucionSillas distribucionSillas) throws Exception;

    void eliminarDistribucionSillas(Integer codigoDistribucion)throws Exception;

    List<DistribucionSillas> listarDistribuciones();

    DistribucionSillas obtenerDistribucionSillas(Integer codigoDistribucion) throws Exception;
}
