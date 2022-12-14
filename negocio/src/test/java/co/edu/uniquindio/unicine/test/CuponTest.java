package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        Cupon cupon = new Cupon("Descuento 10%",0.10f, LocalDateTime.parse("2007-12-03T10:15:30"));
        Cupon guardado = cuponRepo.save(cupon);
        Assertions.assertEquals(guardado.getDescuento(), 0.1f);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Cupon buscado = cuponRepo.findById(1).orElse(null);
        cuponRepo.delete(buscado);
        Assertions.assertNull(cuponRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Cupon guardado = cuponRepo.findById(1).orElse(null);
        guardado.setCodigo(1);
        Cupon nuevo = cuponRepo.save(guardado);
        Assertions.assertEquals(1, nuevo.getCodigo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Cupon> buscado = cuponRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Cupon> listaCupones = cuponRepo.findAll();
        listaCupones.forEach(System.out::println);
    }
}
