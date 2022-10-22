package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {


    private ClienteRepo clienteRepo;
    private EmailServicio emailServicio;
    private PeliculaRepo peliculaRepo;
    private CompraRepo compraRepo;
    private ConfiteriaRepo confiteriaRepo;
    private CompraConfiteriaRepo compraConfiteriaRepo;
    private EntradaRepo entradaRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio, PeliculaRepo peliculaRepo, CompraRepo compraRepo, ConfiteriaRepo confiteriaRepo, CompraConfiteriaRepo compraConfiteriaRepo, EntradaRepo entradaRepo) {

        this.clienteRepo = clienteRepo;
        this.emailServicio= emailServicio;
        this.peliculaRepo = peliculaRepo;
        this.compraRepo = compraRepo;
        this.confiteriaRepo= confiteriaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.entradaRepo = entradaRepo;
    }

    @Override
    public Cliente obtenerCliente(Integer cedulaCliente) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findByCedula(cedulaCliente);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

         return guardado.get();
    }

    @Override
    public Cliente login(String correo, String password) throws Exception {
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if (cliente == null) {
            throw new Exception("Los datos de autenticación son incorrectos");
        }

        return cliente;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        boolean cedulaExiste = esCedulaRepetida(cliente.getCedula());
        boolean correoExiste = esRepetido(cliente.getCorreo());

        if (cedulaExiste) {
            throw new Exception("La cédula ya está registrada");
        }

        if (correoExiste) {
            throw new Exception("El correo ya está en uso");
        }

        emailServicio.enviarEmail("Registro en unicine", "Hola ingrese a ese link para validar acceso", cliente.getCorreo());

        return clienteRepo.save(cliente);
    }

    private boolean esRepetido(String correo) {
        //Si es null retornar False
        return clienteRepo.findByCorreo(correo).orElse(null) != null;
    }
    private boolean esCedulaRepetida(Integer cedula) {

        return clienteRepo.findByCedula(cedula).orElse(null) != null;
    }
    @Override //ojooooooo revisarrrr
    public Cliente actualizarCliente(Cliente cliente) throws Exception{

        Optional<Cliente> guardado = clienteRepo.findById(cliente.getCedula());

        if (guardado.isEmpty()){
            throw new Exception("El cliente no existe");
        }

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer cedulaCliente) throws Exception{
        Optional<Cliente> guardado = clienteRepo.findById(cedulaCliente);

        if (guardado.isEmpty()){
            throw new Exception("El cliente no existe");
        }

      clienteRepo.delete(guardado.get());
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarHistorial(Integer cedulaCliente) throws Exception{

        List<Compra> compras = clienteRepo.obtenerCompras(cedulaCliente);
        return compras;
    }

    @Override
    public Compra hacerCompra(Compra compra) throws Exception{
        Cliente cliente = compra.getCliente();
        Funcion funcion = compra.getFuncion();
        cliente.getCompras().add(compra);
        funcion.getCompras().add(compra);
        calcularValorTotal(compra);
        return compraRepo.save(compra);
    }

    public void calcularValorTotal(Compra compra) {
        float valorTotal, valorConfiteria, valorFuncion, valorEntradas, valorDescuento;
        valorConfiteria = calcularValorConfiteria(compra);
        valorFuncion = calcularValorFuncion(compra);
        valorEntradas = calcularValorEntradas(compra);
        valorTotal = valorConfiteria + valorFuncion + valorEntradas;
        valorDescuento = calcularDescuento(compra, valorTotal);

        compra.setValorTotal(valorDescuento);

    }

    private float calcularDescuento(Compra compra, float valorTotal) {
        if(compra.getCuponCliente() == null){
            return valorTotal;
        }
        float descuento = compra.getCuponCliente().getCodigo_cupon().getDescuento();
        return valorTotal - (valorTotal*descuento);
    }

    private float calcularValorEntradas(Compra compra) {
        float valor= 0;
        for (Entrada entradas: compra.getEntradas()) {
            valor += entradas.getPrecio();
        }
        return valor;
    }

    private float calcularValorFuncion(Compra compra) {
        int cantidadEntradas = compra.getEntradas().size();
        return compra.getFuncion().getPrecio() * cantidadEntradas;
    }

    private float calcularValorConfiteria(Compra compra) {
        float valor = 0;

        for (CompraConfiteria confiteria : compra.getCompraConfiterias()) {
            valor += confiteria.getConfiteria().getPrecio() * confiteria.getUnidades();
        }

        return valor;
    }


    @Override
    public boolean redimirCupon(Integer codigoCupon) throws Exception{
        return false;
    }



    @Override
    public List<Pelicula> buscarPelicula(String nombre) {
        List<Pelicula> peliculas = peliculaRepo.buscarPeliculas(nombre);
        return peliculas;
    }
    public CompraConfiteria ingresarConfiteriaCompra(CompraConfiteria confiteria){
        CompraConfiteria nuevo = compraConfiteriaRepo.save(confiteria);
        Compra compra = nuevo.getCompra();
        compra.getCompraConfiterias().add(confiteria);
        return nuevo;
    }

    public Entrada comprarEntradas (Entrada entrada){
        Entrada nuevo = entradaRepo.save(entrada);
        Compra compra = nuevo.getCompra();
        compra.getEntradas().add(nuevo);
        return nuevo;
    }
    @Override
    public List<Confiteria> buscarConfiteria(String nombre){
        List<Confiteria> confiterias =  confiteriaRepo.buscarConfiteria(nombre);
        return confiterias;
    }

    @Override
    public boolean cambiarPassword(Integer cedula) throws Exception {
        return false;
    }
}
