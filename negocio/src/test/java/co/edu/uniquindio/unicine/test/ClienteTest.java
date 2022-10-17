package co.edu.uniquindio.unicine.test;

import ch.qos.logback.core.net.server.Client;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.aspectj.apache.bcel.util.ClassPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "pepito@email.com", "1234", "url", Arrays.asList(tels));
        Cliente guardado = clienteRepo.save(cliente);
        Assertions.assertEquals(guardado.getNombre(), "pepito");

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Cliente buscado = clienteRepo.findById(1094899).orElse(null);
        clienteRepo.delete(buscado);
        Assertions.assertNull(clienteRepo.findById(1094899).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Cliente guardado = clienteRepo.findById(1094899).orElse(null);
        guardado.setCorreo("Jfmd@uqvirtual.com");
        Cliente nuevo = clienteRepo.save(guardado);
        Assertions.assertEquals("Jfmd@uqvirtual.com", nuevo.getCorreo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Cliente> buscado = clienteRepo.findById(1094899);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Cliente> listaClientes = clienteRepo.findAll();

        listaClientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorEmail() {

        Cliente cliente = clienteRepo.obtener("jf@uqvirtual.co");
        Assertions.assertNotNull(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion() {

        Cliente cliente = clienteRepo.findByCorreoAndPassword("jf@uqvirtual.co","12345");
        Assertions.assertNotNull(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginador() {

        List<Cliente> clientes = clienteRepo.findAll(PageRequest.of(1,2)).toList();
        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginadorEstado() {

        List<Cliente> clientes = clienteRepo.obtenerPorEstado(false, PageRequest.of(0,2));
        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarPorNombre() {

        List<Cliente> clientes = clienteRepo.findAll(Sort.by("nombre"));
        clientes.forEach(System.out::println);
    }


}
