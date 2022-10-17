package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.DistribucionSillas;
import co.edu.uniquindio.unicine.repo.DistribucionSillasRepo;
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
public class DistribucionSillasTest {

    @Autowired
    private DistribucionSillasRepo distribucionSillasRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        DistribucionSillas guardado = distribucionSillasRepo.save(distribucionSillas);
        Assertions.assertEquals(guardado.getFilas(), 4);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        DistribucionSillas buscado = distribucionSillasRepo.findById(1).orElse(null);
        distribucionSillasRepo.delete(buscado);
        Assertions.assertNull(distribucionSillasRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        DistribucionSillas guardado = distribucionSillasRepo.findById(1).orElse(null);
        guardado.setColumnas(5);
        DistribucionSillas nuevo = distribucionSillasRepo.save(guardado);
        Assertions.assertEquals(nuevo.getColumnas(), 5);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<DistribucionSillas> buscado = distribucionSillasRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<DistribucionSillas> listaDistribucionSillas = distribucionSillasRepo.findAll();

        listaDistribucionSillas.forEach(System.out::println);
    }
}
