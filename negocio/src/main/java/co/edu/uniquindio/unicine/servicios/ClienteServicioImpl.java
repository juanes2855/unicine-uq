package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncionDTO;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.stereotype.Service;

import javax.swing.*;
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
    private CuponRepo cuponRepo;

    private CuponClienteRepo cuponClienteRepo;

    private int codigoVerificacion = 0;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio, PeliculaRepo peliculaRepo, CompraRepo compraRepo, ConfiteriaRepo confiteriaRepo, CompraConfiteriaRepo compraConfiteriaRepo, EntradaRepo entradaRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo) {

        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.peliculaRepo = peliculaRepo;
        this.compraRepo = compraRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.entradaRepo = entradaRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
    }

    @Override
    public Cliente obtenerCliente(Integer cedulaCliente) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findByCedula(cedulaCliente);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        return guardado.get();
    }

    public Cliente obtenerClienteXCorreo(String correo) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findByCorreo(correo);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        return guardado.get();
    }

    @Override
    public Cliente login(String correo, String password) throws Exception {

        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if (cliente != null) {
            if (cliente.getEstado()) {
                return cliente;
            }else{
                throw new Exception("Por favor active su cuenta");
            }
        }else{
            throw new Exception("Los datos de autenticación son incorrectos");
        }


    }

    @Override
    public void validarEstadoCuenta(Cliente cliente, String codigo) throws Exception {

        if (codigo.equals(codigoVerificacion)) {
            cliente.setEstado(true);
            clienteRepo.save(cliente);
        }
        throw new Exception("El codigo de verificacion es incorrecto");
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

        Cliente registro = clienteRepo.save(cliente);
        cliente.setEstado(false);
        asignarCuponRegistro(cliente);
        enviarCorreo(cliente);

        return registro;
    }

    private void asignarCuponRegistro(Cliente cliente) {
        Cupon cupon = cuponRepo.findByDescripcion("Descuento 15%");
        CuponCliente cuponCliente = new CuponCliente(true, cliente, cupon);
        cuponClienteRepo.save(cuponCliente);
    }

    @Override
    public void enviarCorreo(Cliente cliente) {
        codigoVerificacion = (int) Math.floor(Math.random() * (111111 - 999999 + 1) + 999999);
        emailServicio.enviarEmail("Registro en unicine", "Hola ingrese este codigo para validar acceso :" + codigoVerificacion, cliente.getCorreo());
    }

    private boolean esRepetido(String correo) {
        //Si es null retornar False
        return clienteRepo.findByCorreo(correo).orElse(null) != null;
    }

    private boolean esCedulaRepetida(Integer cedula) {

        return clienteRepo.findByCedula(cedula).orElse(null) != null;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findById(cliente.getCedula());

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer cedulaCliente) throws Exception {
        Optional<Cliente> guardado = clienteRepo.findById(cedulaCliente);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        clienteRepo.delete(guardado.get());
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarHistorial(Integer cedulaCliente) throws Exception {

        List<Compra> compras = clienteRepo.obtenerCompras(cedulaCliente);
        return compras;
    }

    @Override
    public Compra hacerCompra(Compra compra) throws Exception {
        if (listarHistorial(compra.getCliente().getCedula()).isEmpty()) {
            asignarCuponPrimeraCompra(compra.getCliente());
        }

        for(Entrada e: compra.getEntradas()){
            e.setPrecio( compra.getFuncion().getPrecio() );
        }

        calcularValorTotal(compra);
        Compra registro = compraRepo.save(compra);
        enviarCorreoDetalleCompra(compra);
        return registro;
    }

    @Override
    public void enviarCorreoDetalleCompra(Compra compra) {
        emailServicio.enviarEmail("Factura compra numero "+compra.getCodigo(), "Este es un resumen de la compra realizada :"+"\n"+"Fecha: "+compra.getFecha()+"\n"+" Pelicula: "+compra.getFuncion().getPelicula()+"\n"+"Total factura: "+compra.getValorTotal() + codigoVerificacion, compra.getCliente().getCorreo());
    }

    private void asignarCuponPrimeraCompra(Cliente cliente) {
        Cupon cupon = cuponRepo.findByDescripcion("Descuento 10%");
        CuponCliente cuponCliente = new CuponCliente(true, cliente, cupon);
        cuponClienteRepo.save(cuponCliente);
    }

    public void calcularValorTotal(Compra compra) {
        float valorTotal, valorConfiteria, valorFuncion, valorEntradas, valorDescuento;
        valorConfiteria = calcularValorConfiteria(compra);
        //valorFuncion = calcularValorFuncion(compra);
        valorEntradas = calcularValorEntradas(compra);
        valorTotal = valorConfiteria + valorEntradas;
        valorDescuento = calcularDescuento(compra, valorTotal);

        compra.setValorTotal(valorDescuento);

    }

    //    private float calcularDescuento(Compra compra, float valorTotal) {
//        if(compra.getCuponCliente() == null){
//            return valorTotal;
//        }
//        float descuento = compra.getCuponCliente().getCodigo_cupon().getDescuento();
//
//        return valorTotal - (valorTotal*descuento);
//    }
    private float calcularDescuento(Compra compra, float valorTotal) {
        if (compra.getCliente().getCodigoCupon() == null) {
            return valorTotal;
        }

        //compra.setCuponCliente(compra.getCuponCliente() ); es oneTo Many como se relaciona entocnes?

       // System.out.println(compra.getCuponCliente().getCodigo_cupon().getDescuento());

        //float descuento = compra.getCuponCliente().getCodigo_cupon().getDescuento();
        float descuento = 0f;

        return valorTotal - (valorTotal * descuento);
    }

    private float calcularValorEntradas(Compra compra) {
        float valor = 0;
        for (Entrada entradas : compra.getEntradas()) {
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
    public boolean redimirCupon(Cliente cliente, Compra compra, Integer codigoCupon) throws Exception {

        Optional<Cupon> cupon = cuponRepo.findById(codigoCupon);

        if (cupon != null) {
            CuponCliente cuponCliente = new CuponCliente(false, cliente, cupon.get());
            if (!cuponCliente.getEstado()) {
                compra.setCuponCliente(cuponCliente);
                cuponCliente.setCompra(compra);
                cliente.getCodigoCupon().add(cuponCliente);
                return true;
            }
        }
        throw new Exception("El cupo no existe");
    }


    @Override
    public List<Pelicula> buscarPelicula(String nombre) {
        List<Pelicula> peliculas = peliculaRepo.buscarPeliculas(nombre);
        return peliculas;

    }
    @Override
    public List<PeliculaFuncionDTO> buscarPeliculasConDTO(String nombre){
        List<PeliculaFuncionDTO> peliculas = peliculaRepo.buscarPeliculasConDTO(nombre);
        return peliculas;
    }

    public CompraConfiteria ingresarConfiteriaCompra(CompraConfiteria confiteria) {
        CompraConfiteria nuevo = compraConfiteriaRepo.save(confiteria);
        Compra compra = nuevo.getCompra();
        compra.getCompraConfiterias().add(confiteria);
        return nuevo;
    }

    public Entrada comprarEntradas(Entrada entrada) {
        Entrada nuevo = entradaRepo.save(entrada);
        Compra compra = nuevo.getCompra();
        compra.getEntradas().add(nuevo);
        return nuevo;
    }

    @Override
    public List<Confiteria> buscarConfiteria(String nombre) {
        List<Confiteria> confiterias = confiteriaRepo.buscarConfiteria(nombre);
        return confiterias;
    }

    @Override
    public boolean cambiarPassword(String correo) throws Exception {
        Optional<Cliente> cliente = clienteRepo.findByCorreo(correo);
        enviarCorreoContraNueva(cliente.get());
        return true;
    }

    @Override
    public void enviarCorreoContraNueva(Cliente cliente) {
        codigoVerificacion = (int) Math.floor(Math.random() * (111111 - 999999 + 1) + 999999);
        cliente.setPassword(String.valueOf(codigoVerificacion));
        emailServicio.enviarEmail("Nueva contraseña", "Hola ingrese esta contraseña para validar acceso :" + codigoVerificacion, cliente.getCorreo());
    }
}
