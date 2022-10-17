package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.DistribucionSillas;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.SalaRepo;
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
public class SalaTest {

    @Autowired
    private SalaRepo salaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
        Sala guardado = salaRepo.save(sala);
        Assertions.assertEquals(guardado.getNombre(), "Cine xd");

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Sala buscado = salaRepo.findById(1).orElse(null);
        salaRepo.delete(buscado);
        Assertions.assertNull(salaRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Sala guardado = salaRepo.findById(1).orElse(null);
        guardado.setNombre("Cine xxx");
        Sala nuevo = salaRepo.save(guardado);
        Assertions.assertEquals(nuevo.getNombre(), "Cine xxx");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Sala> buscado = salaRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Sala> listaSalas = salaRepo.findAll();

        listaSalas.forEach(System.out::println);
    }
}
