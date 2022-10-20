package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdminTeatroServicio {

    Horario crearHorario(Horario horario) throws Exception;
    void eliminarHorario(Horario codigoHorario) throws Exception;
    List<Horario> listarHorarios();
    Horario obtenerHorario(Integer codigoHorario) throws Exception;

    Sala crearSala(Sala sala) throws Exception;
    Sala actualizarSala(Sala sala) throws Exception;
    void eliminarSala(Sala codigoSala) throws Exception;
    List<Sala> listarSalas();
    Sala obtenerSala(Integer codigoSala) throws Exception;

    Funcion crearFuncion(Funcion funcion) throws Exception;
    Funcion actualizarFuncion(Funcion funcion) throws Exception;
    void eliminarFuncion(Funcion codigoFuncion) throws Exception;
    List<Funcion> listarFunciones();
    Funcion obtenerFuncion(Integer codigoFuncion) throws Exception;

    Teatro crearTeatro(Teatro teatro) throws Exception;
    Teatro actualizarTeatro(Teatro teatro) throws Exception;
    void eliminarTeatro(Teatro codigoTeatro) throws Exception;
    List<Teatro> listarTeatros();
    Teatro obtenerTeatro(Integer codigoTeatro) throws Exception;

    AdministradorTeatro login(String correo , String password) throws Exception;
}
