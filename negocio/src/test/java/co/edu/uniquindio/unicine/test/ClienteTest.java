package co.edu.uniquindio.unicine.test;

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
    public void registrar(){

        String[] tels = new String[] {"231","323"};
        Cliente cliente = new Cliente(12345, "pepito","pepito@email.com","1234", "url", Arrays.asList(tels));
        Cliente guardado = clienteRepo.save(cliente);
        Assertions.assertEquals(guardado.getNombre(), "pepito");

    }

    @Test
    public void eliminar(){
        String[] tels = new String[] {"231","323"};
        Cliente cliente = new Cliente(12345, "pepito","pepito@email.com","1234", "url", Arrays.asList(tels));
        Cliente guardado = clienteRepo.save(cliente);
        clienteRepo.delete(guardado);
      //  Optional<Cliente> buscado = clienteRepo.findAllById(12345);
      //  Assertions.assertNull(buscado.orElse(null));
    }
    public void actualizar(){

    }
    public void obtener(){

    }
    @Sql("classpath:dataset.sql")
    public void listar(){
        List<Cliente> listaClientes = clienteRepo.findAll();
        System.out.println(listaClientes);

    }

}
