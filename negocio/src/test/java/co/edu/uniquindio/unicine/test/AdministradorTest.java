package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.repo.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        String[] tels = new String[]{"231", "323"};
        Administrador administrador = new Administrador(12345, "pepito", "pepito@email.com", "1234");
        Administrador guardado = administradorRepo.save(administrador);
        Assertions.assertEquals(guardado.getNombre(), "pepito");

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Administrador buscado = administradorRepo.findById(1094899).orElse(null);
        administradorRepo.delete(buscado);
        Assertions.assertNull(administradorRepo.findById(1094899).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Administrador guardado = administradorRepo.findById(1094899).orElse(null);
        guardado.setCorreo("Jfmd@uqvirtual.com");
        Administrador nuevo = administradorRepo.save(guardado);
        Assertions.assertEquals("Jfmd@uqvirtual.com", nuevo.getCorreo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Administrador> buscado = administradorRepo.findById(1094899);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Administrador> listaAdministradores = administradorRepo.findAll();

        listaAdministradores.forEach(System.out::println);
    }

}


