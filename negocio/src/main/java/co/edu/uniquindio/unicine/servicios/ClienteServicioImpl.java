package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncionDTO;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    private FuncionRepo funcionRepo;

    private CuponClienteRepo cuponClienteRepo;

    private int codigoVerificacion = 0;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio, PeliculaRepo peliculaRepo,
                               CompraRepo compraRepo, ConfiteriaRepo confiteriaRepo, CompraConfiteriaRepo compraConfiteriaRepo,
                               EntradaRepo entradaRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo,
                                FuncionRepo funcionRepo) {

        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.peliculaRepo = peliculaRepo;
        this.compraRepo = compraRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.entradaRepo = entradaRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.funcionRepo = funcionRepo;
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

        Cliente cliente = clienteRepo.findByCorreo(correo).orElse(null);

        if (cliente == null) {
            throw new Exception("EL correo no existe");
            }

        if(!cliente.getEstado()){
            throw new Exception("La cuenta no esta activada");
        }

        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        if(!spe.checkPassword(password, cliente.getPassword())){
            throw new Exception("La contraseña es incorrecta");
        }
        return  cliente;

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
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        cliente.setPassword(spe.encryptPassword(cliente.getPassword()));

        Cliente registro = clienteRepo.save(cliente);
        cliente.setEstado(false);
        asignarCuponRegistro(cliente);


        ZonedDateTime zd = LocalDateTime.now().atZone(ZoneId.of("America/Bogota"));
        AES256TextEncryptor tx = new AES256TextEncryptor();
        tx.setPassword("teclado");
        String paramet1 = tx.encrypt(registro.getCorreo());
        String paramet2 = tx.encrypt(""+zd.toInstant().toEpochMilli());
        //enviarCorreo(cliente);
        emailServicio.enviarEmail("Registro en unicine", "Hola debe ir al siguiente enlace para activar la cuenta : http://localhost:8081/activar_cuenta.xhtml?p1="+paramet1+"&p2="+paramet2, cliente.getCorreo());


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
    public void activarCliente(String correo, String fecha) throws Exception{

        correo = correo.replaceAll(" ", "+");
        fecha = fecha.replaceAll(" ", "+");

        ZonedDateTime zd = LocalDateTime.now().atZone(ZoneId.of("America/Bogota"));
        AES256TextEncryptor tx = new AES256TextEncryptor();
        tx.setPassword("teclado");

        String correoDes = tx.decrypt(correo);
        String fechaDes = tx.decrypt(fecha);

        Cliente guardado = clienteRepo.findByCorreo(correoDes).orElse(null);

        if (guardado == null){
            throw  new Exception("El cliente no existe");
        }
        guardado.setEstado(true);
        clienteRepo.save(guardado);
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
    public List<Pelicula> listarPorEstadoCiudad(EstadoPelicula estadoPelicula, Integer codigoCiudad) throws Exception {
        List<Pelicula> peliculas = funcionRepo.buscarPeliculaEstadoCiudad(estadoPelicula, codigoCiudad);
        return peliculas;
    }

    @Override
    public List<Pelicula> listarPorEstado(EstadoPelicula estadoPelicula) throws Exception {
        List<Pelicula> peliculas = funcionRepo.buscarPeliculaEstado(estadoPelicula);
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

    @Override
    public Compra hacerCompra(Cliente cliente, Funcion funcion, MedioPago medioPagoSeleccionado, List<CompraConfiteria> lista, Integer codigoCupon) {
        return null;
    }

    @Override
    public List<Teatro> listarTeatrosPeliculaDia(Integer codigoCiudad, Integer codigoPelicula, LocalDate fecha) {
        return funcionRepo.listarTeatrosPeliculaDia(codigoCiudad, codigoPelicula, fecha);
    }

    @Override
    public List<Funcion> listarFuncionesPeliculaDia(Integer codigoCiudad, Integer codigoPelicula, LocalDate fecha) {
        return funcionRepo.listarFuncionesPeliculaDia(codigoCiudad, codigoPelicula, fecha); // diaSemana ???
    }

    @Override
    public Compra obtenerCompra(Integer codigo) throws Exception {
        return compraRepo.findById(codigo).orElseThrow(() -> new Exception("No se encontro la compra"));
    }
}
