package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
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
public class TeatroTest {

    @Autowired
    private TeatroRepo teatroRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        Teatro guardado = teatroRepo.save(teatro);
        Assertions.assertEquals(guardado.getTelefono(), "1231232");

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Teatro buscado = teatroRepo.findById(1).orElse(null);
        teatroRepo.delete(buscado);
        Assertions.assertNull(teatroRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Teatro guardado = teatroRepo.findById(1).orElse(null);
        guardado.setTelefono("123");
        Teatro nuevo = teatroRepo.save(guardado);
        Assertions.assertEquals(nuevo.getTelefono(), "123");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Teatro> buscado = teatroRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Teatro> listaTeatros = teatroRepo.findAll();

        listaTeatros.forEach(System.out::println);
    }

}
