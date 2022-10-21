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
        return ciudadRepo.save(ciudad);
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
       boolean peliculaExiste = esPeliculaRepetida(pelicula.getCodigo());
       
       if (peliculaExiste)
           throw new Exception("La pelicula ya está registrada");
       
       
        return peliculaRepo.save(pelicula);
    }

    private boolean esPeliculaRepetida(Integer codigo) {
        return peliculaRepo.findById(codigo).orElse(null) != null;
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

        return peliculaRepo.findById(codigoPelicula).orElse(null);
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
        boolean cuponExiste = esCuponRepetido(cupon.getCodigo());
        if (cuponExiste) 
            throw new Exception("El cupon ya está en uso");
        
        
        return cuponRepo.save(cupon);
    }

    private boolean esCuponRepetido(Integer codigo) {
        return cuponRepo.findById(codigo).orElse(null ) != null;
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
            throw new Exception("La administrador  ya está registrada");


            return administradorTeatroRepo.save(administradorTeatro);
    }

    private boolean esAdminTeatroRepetido(Integer cedula) {
        return administradorTeatroRepo.findById(cedula).orElse(null) != null;
    }

    @Override
    public AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro cliente) throws Exception {
        return null;
    }

    @Override
    public void eliminarAdministradorTeatro(Integer cedulaAdministradorTeatro) throws Exception {

    }

    @Override
    public List<AdministradorTeatro> listarAdministradorTeatro() {
        return null;
    }

    @Override
    public AdministradorTeatro obtenerAdministradorTeatro(Integer cedula) throws Exception {
        return null;
    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {
        return null;
    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {
        return null;
    }

    @Override
    public void eliminarConfiteria(Integer codigoConfiteria) throws Exception {

    }

    @Override
    public List<Confiteria> listarConfiterias() {
        return null;
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {
        return null;
    }
}
