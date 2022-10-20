package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest(){

        Cliente cliente = Cliente.builder().cedula(123456).nombre("Juanito").password("123a").correo("juanito@email.com").urlFoto("http...").build();

        try {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTest(){

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
    public void eliminarClienteTest(){

        try {
            clienteServicio.eliminarCliente(1078954);
            Cliente cliente = clienteServicio.obtenerCliente(1078954);
            Assertions.assertNotNull(cliente);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarClienteTest(){

        List<Cliente> lista = clienteServicio.listarClientes();
        lista.forEach(System.out::println);

    }
}
