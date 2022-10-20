package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.HorarioRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio{

    private final HorarioRepo horarioRepo;
    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;
    private final TeatroRepo teatroRepo;

    public AdminTeatroServicioImpl(HorarioRepo horarioRepo, SalaRepo salaRepo, FuncionRepo funcionRepo, TeatroRepo teatroRepo) {
        this.horarioRepo = horarioRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.teatroRepo = teatroRepo;
    }

    @Override
    public Horario crearHorario(Horario horario) throws Exception {
        return null;
    }

    @Override
    public void eliminarHorario(Horario codigoHorario) throws Exception {

    }

    @Override
    public List<Horario> listarHorarios() {
        return null;
    }

    @Override
    public Horario obtenerHorario(Integer codigoHorario) throws Exception {
        return null;
    }

    @Override
    public Sala crearSala(Sala sala) throws Exception {
        return null;
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        return null;
    }

    @Override
    public void eliminarSala(Sala codigoSala) throws Exception {

    }

    @Override
    public List<Sala> listarSalas() {
        return null;
    }

    @Override
    public Sala obtenerSala(Integer codigoSala) throws Exception {
        return null;
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {
        return null;
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        return null;
    }

    @Override
    public void eliminarFuncion(Funcion codigoFuncion) throws Exception {

    }

    @Override
    public List<Funcion> listarFunciones() {
        return null;
    }

    @Override
    public Funcion obtenerFuncion(Integer codigoFuncion) throws Exception {
        return null;
    }

    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {
        return null;
    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        return null;
    }

    @Override
    public void eliminarTeatro(Teatro codigoTeatro) throws Exception {

    }

    @Override
    public List<Teatro> listarTeatros() {
        return null;
    }

    @Override
    public Teatro obtenerTeatro(Integer codigoTeatro) throws Exception {
        return null;
    }

    @Override
    public AdministradorTeatro login(String correo, String password) throws Exception {
        return null;
    }
}
