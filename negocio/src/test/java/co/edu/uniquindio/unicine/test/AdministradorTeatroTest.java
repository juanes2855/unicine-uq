package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTeatroTest {

    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        String[] tels = new String[]{"231", "323"};
        AdministradorTeatro administradorTeatro = new AdministradorTeatro(12345, "pepito", "pepito@email.com", "1234");
        AdministradorTeatro guardado = administradorTeatroRepo.save(administradorTeatro);
        Assertions.assertEquals(guardado.getNombre(), "pepito");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        AdministradorTeatro buscado = administradorTeatroRepo.findById(1091899).orElse(null);
        administradorTeatroRepo.delete(buscado);
        Assertions.assertNull(administradorTeatroRepo.findById(1094899).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        AdministradorTeatro guardado = administradorTeatroRepo.findById(1091899).orElse(null);
        guardado.setCorreo("mdjf@uqvirtual.com");
        AdministradorTeatro nuevo = administradorTeatroRepo.save(guardado);
        Assertions.assertEquals("mdjf@uqvirtual.com", nuevo.getCorreo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<AdministradorTeatro> buscado = administradorTeatroRepo.findById(1091899);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<AdministradorTeatro> listaAdministradoresTeatro = administradorTeatroRepo.findAll();

        listaAdministradoresTeatro.forEach(System.out::println);
    }
}
