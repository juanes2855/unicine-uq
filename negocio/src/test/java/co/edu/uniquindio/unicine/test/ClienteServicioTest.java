package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest() {

        Cliente cliente = Cliente.builder().cedula(123456).nombre("Juanito").password("123a").correo("juanito@email.com").urlFoto("http...").build();

        try {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }

//    @Test
//    @Sql("classpath:dataset.sql")
//    public void validarEstadoCuenta() {
//
//        Cliente cliente = Cliente.builder().cedula(123456).nombre("Juanito").password("123a").correo("juanes13916@gmail.com").urlFoto("http...").build();
//
//        try {
//            Cliente nuevo = clienteServicio.registrarCliente(cliente);
//            clienteServicio.validarEstadoCuenta(nuevo);
//            Assertions.assertNotNull(nuevo);
//        } catch (Exception e) {
//            Assertions.assertTrue(false);
//        }
//
//    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTest() {

        try {
            Cliente cliente = clienteServicio.obtenerCliente(1078954);
            cliente.setNombre("Nuevo nombre");
            Cliente nuevo = clienteServicio.actualizarCliente(cliente);
            Assertions.assertEquals("Nuevo nombre", nuevo.getNombre());
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest() {

        try {
            clienteServicio.eliminarCliente(1004917021);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try {
            // Cliente cliente = clienteServicio.obtenerCliente(1004917021);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClienteTest() {

        List<Cliente> lista = clienteServicio.listarClientes();
        lista.forEach(System.out::println);

    }

    @Test
    public void enviarCorreo() {
        emailServicio.enviarEmail("ASUNTO", "CONTENIDO", "juanes13916@gmail.com");
    }

    @Test
    public void enviarCorreoCliente() {
        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "pepito@email.com", "1234", "url", Arrays.asList(tels));
        clienteServicio.enviarCorreo(cliente);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void agregarEntrada() {
        MedioPago medioPago = MedioPago.TARJETA_UNICINE;


        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "pepito@email.com", "1234", "url", Arrays.asList(tels));
        try {
            clienteServicio.registrarCliente(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Horario horario = new Horario("LMXJVSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
        Genero[] generos = new Genero[]{Genero.TERROR};
        Pelicula pelicula = new Pelicula("El sismo", EstadoPelicula.CARTELERA, "De la naturaleza nadie se salva",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4, 4);
        Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
        Funcion funcion = new Funcion(24000f, horario, sala, pelicula);

        Compra compra = new Compra(medioPago, cliente, funcion);
        compra.setValorTotal(50000f);
        Entrada entrada = new Entrada(15000f, 2, 4);
        entrada.setCompra(compra);
        try {
            Entrada nuevo = clienteServicio.comprarEntradas(entrada);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompra() {
        MedioPago medioPago = MedioPago.TARJETA_UNICINE;


        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "jfmunozd@uqvirtual.edu.co", "1234", "url", Arrays.asList(tels));

        Horario horario = new Horario("LMXJVSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
        horario.setCodigo(1);
        Genero[] generos = new Genero[]{Genero.TERROR};
        Pelicula pelicula = new Pelicula("El sismo", EstadoPelicula.CARTELERA, "De la naturaleza nadie se salva",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        pelicula.setCodigo(1);
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        ciudad.setCodigo(1);
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        teatro.setCodigo(1);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4, 4);
        distribucionSillas.setCodigo(1);
        Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
        sala.setCodigo(1);
        Funcion funcion = new Funcion(24000f, horario, sala, pelicula);
        funcion.setCodigo(1);
        Compra compra = new Compra(medioPago, cliente, funcion);
        compra.setCodigo(1);
        Entrada entrada = new Entrada(15000f, 2, 4);
        entrada.setCompra(compra);
       // Confiteria confiteria = new Confiteria("Platano asado", 15000f, "url");
        //CompraConfiteria compraConfiteria = new CompraConfiteria(30000f, 2, compra, confiteria);
       // compra.getEntradas().add(entrada);
       // compra.getCompraConfiterias().add(compraConfiteria);
        try {
            Compra nuevo = clienteServicio.hacerCompra(compra);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaTest() {

        List<Pelicula> lista = clienteServicio.buscarPelicula("we");
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHistorialTest() throws Exception {

        List<Compra> lista = clienteServicio.listarHistorial(1091003);
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPassword() throws Exception {

        Boolean cambio = clienteServicio.cambiarPassword("jfmunozd@uqvirtual.edu.co");

        Assertions.assertTrue(cambio);

    }


}
