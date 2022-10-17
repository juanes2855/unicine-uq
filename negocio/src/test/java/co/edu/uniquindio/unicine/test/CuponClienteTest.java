package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.repo.CuponClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponClienteTest {

    @Autowired
    private CuponClienteRepo cuponClienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "pepito@email.com", "1234", "url", Arrays.asList(tels));
        Cupon cupon = new Cupon("Descuento 10%",0.10f, LocalDateTime.parse("2007-12-03T10:15:30"));
        CuponCliente cuponCliente = new CuponCliente(true, cliente, cupon);
        CuponCliente guardado = cuponClienteRepo.save(cuponCliente);
        Assertions.assertEquals(guardado.getCedula_cliente().getNombre(), "pepito");
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        CuponCliente buscado = cuponClienteRepo.findById(1).orElse(null);
        cuponClienteRepo.delete(buscado);
        Assertions.assertNull(cuponClienteRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        CuponCliente guardado = cuponClienteRepo.findById(1).orElse(null);
        guardado.setCodigo(1);
        CuponCliente nuevo = cuponClienteRepo.save(guardado);
        Assertions.assertEquals(1, nuevo.getCodigo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<CuponCliente> buscado = cuponClienteRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<CuponCliente> listaCuponClientes = cuponClienteRepo.findAll();
        listaCuponClientes.forEach(System.out::println);
    }



}
