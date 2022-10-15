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
import java.util.Arrays;
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

        String[] tels = new String[]{"231", "323"};
        Cupon cupon = new Cupon("Descuento 10%",0.10f, LocalDateTime.parse("2007-12-03T10:15:30"));
        Cupon guardado = cuponRepo.save(cupon);
        Assertions.assertEquals(guardado.getCodigo(), 9);

    }


}
