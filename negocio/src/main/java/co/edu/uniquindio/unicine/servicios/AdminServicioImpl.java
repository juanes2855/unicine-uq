package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServicioImpl implements AdminServicio{

    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final CuponRepo cuponRepo;
    private final CiudadRepo ciudadRepo;
    private final ConfiteriaRepo confiteriaRepo;

    public AdminServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo, CiudadRepo ciudadRepo, ConfiteriaRepo confiteriaRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.ciudadRepo = ciudadRepo;
        this.confiteriaRepo = confiteriaRepo;
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
        return null;
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        return null;
    }

    @Override
    public void eliminarPelicula(Integer codigoPelicula) throws Exception {

    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return null;
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception {
        return null;
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
        return null;
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        return null;
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {

    }

    @Override
    public List<Cupon> listarCupones() {
        return null;
    }

    @Override
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        return null;
    }

    @Override
    public Administrador login(String correo, String password) throws Exception {
        return null;
    }

    @Override
    public AdministradorTeatro registrarAdministradorTeatro(AdministradorTeatro cliente) throws Exception {
        return null;
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
