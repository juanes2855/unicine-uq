package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServicioImpl implements AdminServicio{

    private final AdministradorRepo administradorRepo;
    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final CuponRepo cuponRepo;
    private final CiudadRepo ciudadRepo;
    private final ConfiteriaRepo confiteriaRepo;

    public AdminServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo, CiudadRepo ciudadRepo, ConfiteriaRepo confiteriaRepo, AdministradorRepo administradorRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.ciudadRepo = ciudadRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception {
    /*    boolean ciudadExiste  = esCiudadRepetida(ciudad.getCodigo());
        if (ciudadExiste)
            throw new Exception("La ciudad ya está registrada");
    */
        return ciudadRepo.save(ciudad);
    }

    private boolean esCiudadRepetida(Integer codigo) {
        return peliculaRepo.findById(codigo).orElse(null) != null;
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception {
        Optional<Ciudad> ciudad = ciudadRepo.findById(codigoCiudad);
        if(ciudad.isEmpty()){
            throw new Exception("No existe una ciudad con ese codigo");
        }

        return ciudad.get();
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {
            // verificar si necesita validacion
        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(pelicula.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El pelicula no existe");
        }
        
        return peliculaRepo.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer codigoPelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(codigoPelicula);

        if (guardado.isEmpty()){
            throw new Exception("El pelicula no existe");
        }

        peliculaRepo.delete(guardado.get());
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepo.findAll();
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(codigoPelicula);
        if (guardado.isEmpty())
            throw new Exception("La pelicula no existe");

        return guardado.get();
    }


    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {

        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {

        Optional<Cupon> guardado = cuponRepo.findById(cupon.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        } 
        
        return cuponRepo.save(cupon);
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {

        Optional<Cupon> guardado = cuponRepo.findById(codigoCupon);

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }

        cuponRepo.delete(guardado.get());
    }

    @Override
    public List<Cupon> listarCupones() {
        return cuponRepo.findAll();
    }

    @Override
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigoCupon);
        if (guardado.isEmpty())
            throw new Exception("El cupon no existe");

        return guardado.get();
    }

    @Override
    public Administrador login(String correo, String password) throws Exception {
        Administrador administrador = administradorRepo.comprobarAutenticacion(correo, password);

        if(administrador == null)
            throw new Exception("Los datos de autenticación son incorrectos");


        return administrador;
    }

    @Override
    public AdministradorTeatro registrarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception {
        boolean administradorTeatroExiste = esAdminTeatroRepetido(administradorTeatro.getCedula());

        if (administradorTeatroExiste)
            throw new Exception("El administrador  ya está registrado");


            return administradorTeatroRepo.save(administradorTeatro);
    }

    private boolean esAdminTeatroRepetido(Integer cedula) {
        return administradorTeatroRepo.findById(cedula).orElse(null) != null;
    }

    @Override
    public AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception {
       Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(administradorTeatro.getCedula());
        if (guardado.isEmpty())
            throw new Exception("El administrador teatro no existe");


        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public void eliminarAdministradorTeatro(Integer cedulaAdministradorTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(cedulaAdministradorTeatro);
        if (guardado.isEmpty())
            throw new Exception("El administrador teatro no existe");

        administradorTeatroRepo.delete(guardado.get());
    }

    @Override
    public List<AdministradorTeatro> listarAdministradorTeatro() {
        return administradorTeatroRepo.findAll();
    }

    @Override
    public AdministradorTeatro obtenerAdministradorTeatro(Integer cedulaAdministradorTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(cedulaAdministradorTeatro);
        if (guardado.isEmpty())
            throw new Exception("El administrador teatro no existe");

        return guardado.get();
    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {

        boolean confiteriaExiste = esConfiteriaRepetida(confiteria.getNombre());

        if (confiteriaExiste)
            throw new Exception("La confiteria ya esta registrada");

        return confiteriaRepo.save(confiteria);
    }

    private boolean esConfiteriaRepetida(String nombre) {
        return confiteriaRepo.buscarConfiteriaxNombre(nombre).orElse(null ) != null;
    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepo.findById(confiteria.getCodigo());
        if (guardado.isEmpty())
            throw new Exception("La confiteria no existe");

        return confiteriaRepo.save(confiteria);
    }

    @Override
    public void eliminarConfiteria(Integer codigoConfiteria) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepo.findById(codigoConfiteria);
        if (guardado.isEmpty())
            throw new Exception("La confiteria no existe");

        confiteriaRepo.delete(guardado.get());
    }

    @Override
    public List<Confiteria> listarConfiterias() {
        return confiteriaRepo.findAll();
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepo.findById(codigoConfiteria);
        if (guardado.isEmpty())
            throw new Exception("La confiteria no existe");

        return guardado.get();
    }
}
