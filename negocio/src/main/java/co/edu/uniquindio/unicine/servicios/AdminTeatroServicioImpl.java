package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.HorarioRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        boolean horarioExiste = esHorarioValido(horario);
        if (horarioExiste)
            throw new Exception("El  horario ya existe");

        return horarioRepo.save(horario);
    }
    @Override
    public boolean esHorarioValido(Horario horario) throws Exception{
        boolean horaValida = validarHora(horario.getHora());
        boolean fechasValidas = validarRangoFechas(horario.getFechaInicio(), horario.getFechaFin());
        if(!fechasValidas)
            throw new Exception("La fecha final es inferior igual a la inicial");

        if(!horaValida)
            throw new Exception("La hora no es valida");

        Horario nuevo = horarioRepo.encontrarHorarioRepetido(horario.getDia(), horario.getHora());
        return (nuevo != null)?true : false;

    }
    @Override
    public boolean validarRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return (fechaInicio.isBefore(fechaFin))? true : false;
    }

    @Override
    public boolean validarHora(String hora) throws Exception {
       String horaHorario = hora.split(":")[0];
       String minHorario = hora.split(":")[1];

       return (minHorario.equals("00") || minHorario.equals("30")) ? true : false;

    }

    @Override
    public void eliminarHorario(Horario horario) throws Exception {

        Optional<Horario> guardado = horarioRepo.findById(horario.getCodigo());

        if (guardado.isEmpty()) {
            throw new Exception("El pelicula no existe");
        }

        horarioRepo.delete(guardado.get());
    }


    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }

    @Override
    public Horario obtenerHorario(Integer codigoHorario) throws Exception {
        Optional<Horario> guardado = horarioRepo.findById(codigoHorario);
        if (guardado.isEmpty())
            throw new Exception("El cupon no existe");

        return guardado.get();
    }

    @Override
    public Sala crearSala(Sala sala) throws Exception {
        boolean salaExiste = esSalaRepetida(sala.getNombre());
        if (salaExiste)
            throw new Exception("El cupon ya está en uso");

        return salaRepo.save(sala);

    }

    private boolean esSalaRepetida(String nombre) {
        Sala sala = salaRepo.findByNombre(nombre);
        return (sala != null)? true : false;
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(sala.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }

        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Sala sala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(sala.getCodigo());

        if (guardado.isEmpty()) {
            throw new Exception("El pelicula no existe");
        }

        salaRepo.delete(guardado.get());
    }

    @Override
    public List<Sala> listarSalas() {
        return salaRepo.findAll();
    }

    @Override
    public Sala obtenerSala(Integer codigoSala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(codigoSala);
        if (guardado.isEmpty())
            throw new Exception("El cupon no existe");

        return guardado.get();
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {

        Sala sala = funcion.getSala();

        sala.getFunciones().add(funcion);
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
