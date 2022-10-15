package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.repo.ConfiteriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConfiteriaTest {

    @Autowired
    private ConfiteriaRepo confiteriaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        String[] tels = new String[]{"231", "323"};
        Confiteria cliente = new Confiteria();
        Confiteria guardado = confiteriaRepo.save(cliente);
        Assertions.assertEquals(guardado.getNombre(), "pepito");

    }
}
