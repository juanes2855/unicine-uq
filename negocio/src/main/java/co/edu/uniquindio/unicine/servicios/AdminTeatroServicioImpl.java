package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
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

    private final DistribucionSillasRepo distribucionSillasRepo;

    private final AdministradorTeatroRepo administradorTeatroRepo;

    public AdminTeatroServicioImpl(HorarioRepo horarioRepo, SalaRepo salaRepo, FuncionRepo funcionRepo, TeatroRepo teatroRepo, AdministradorTeatroRepo administradorTeatroRepo,  DistribucionSillasRepo distribucionSillasRepo) {
        this.horarioRepo = horarioRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.teatroRepo = teatroRepo;
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.distribucionSillasRepo = distribucionSillasRepo;
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
    public void eliminarHorario(Integer horario) throws Exception {

        Optional<Horario> guardado = horarioRepo.findById(horario);

        if (guardado.isEmpty()) {
            throw new Exception("El pelicula no existe");
        }

        horarioRepo.delete(guardado.get());
    }


    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }

//    @Override
//    public Horario obtenerHorario(Integer codigoHorario) throws Exception {
//        Optional<Horario> guardado = horarioRepo.findById(codigoHorario);
//        if (guardado.isEmpty())
//            throw new Exception("El cupon no existe");
//
//        return guardado.get();
//    }


    @Override
    public DistribucionSillas registrarDistribucionSilla(DistribucionSillas distribucionSillas) throws Exception {
        return distribucionSillasRepo.save(distribucionSillas);
    }

    @Override
    public void eliminarDistribucionSillas(Integer codigoDistribucion) throws Exception {
        Optional<DistribucionSillas> guardado = distribucionSillasRepo.findById(codigoDistribucion);

        if (guardado.isEmpty()) {
            throw new Exception("La distribucion de sillas no existe");
        }

        distribucionSillasRepo.delete(guardado.get());
    }

    @Override
    public List<DistribucionSillas> listarDistribuciones() {
        return distribucionSillasRepo.findAll();
    }

    @Override
    public DistribucionSillas obtenerDistribucionSillas(Integer codigoDistribucion) throws Exception {
        Optional<DistribucionSillas> guardado = distribucionSillasRepo.findById(codigoDistribucion);

        if (guardado.isEmpty()) {
            throw new Exception("La distribucion de sillas no existe");
        }

        return guardado.get();
    }

    @Override
    public Sala crearSala(Sala sala) throws Exception {
        Sala nuevo = salaRepo.save(sala);
        Teatro teatro = nuevo.getTeatro();
        teatro.getSalas().add(nuevo);
        return nuevo;

    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(sala.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("La sala no existe");
        }

        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer sala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(sala);

        if (guardado.isEmpty()) {
            throw new Exception("La sala no existe");
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
            throw new Exception("La sala no existe");

        return guardado.get();
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {
        Pelicula pelicula = funcion.getPelicula();
        Horario horario = funcion.getHorario();
        Sala sala = funcion.getSala();

        horario.getFunciones().add(funcion);
        pelicula.getFunciones().add(funcion);
        sala.getFunciones().add(funcion);
        return funcionRepo.save(funcion);
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(funcion.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("La funcion no existe");
        }

        return funcionRepo.save(funcion);
    }

    @Override
    public void eliminarFuncion(Integer  funcion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(funcion);

        if (guardado.isEmpty()) {
            throw new Exception("La funcion no existe");
        }

        funcionRepo.delete(guardado.get());
    }

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepo.findAll();
    }

    @Override
    public Funcion obtenerFuncion(Integer codigoFuncion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(codigoFuncion);
        if (guardado.isEmpty())
            throw new Exception("La funcion no existe");

        return guardado.get();
    }

    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {
        Teatro nuevoTeatro = teatroRepo.save(teatro);
        Ciudad ciudad = teatro.getCiudad();
        ciudad.getTeatros().add(nuevoTeatro);
        return nuevoTeatro;
    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        Optional<Teatro> guardado = teatroRepo.findById(teatro.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El teatro no existe");
        }

        return teatroRepo.save(teatro);
    }

    @Override
    public void eliminarTeatro(Integer teatro) throws Exception {
        Optional<Teatro> guardado = teatroRepo.findById(teatro);

        if (guardado.isEmpty()) {
            throw new Exception("El teatro no existe");
        }

        teatroRepo.delete(guardado.get());
    }

    @Override
    public List<Teatro> listarTeatros() {
        return teatroRepo.findAll();
    }

    @Override
    public Teatro obtenerTeatro(Integer codigoTeatro) throws Exception {
        Optional<Teatro> guardado = teatroRepo.findById(codigoTeatro);
        if (guardado.isEmpty())
            throw new Exception("El teatro no existe");

        return guardado.get();
    }

    @Override
    public AdministradorTeatro login(String correo, String password) throws Exception {
        AdministradorTeatro administradorTeatro = administradorTeatroRepo.comprobarAutenticacion(correo, password);

        if(administradorTeatro == null)
            throw new Exception("Los datos de autenticación son incorrectos");


        return administradorTeatro;
    }
}
