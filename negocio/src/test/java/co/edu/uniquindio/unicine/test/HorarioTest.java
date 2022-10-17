package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.repo.HorarioRepo;
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
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        Horario horario = new Horario(17, "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
        Horario guardado = horarioRepo.save(horario);
        Assertions.assertEquals(guardado.getDia(), 17);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Horario buscado = horarioRepo.findById(1).orElse(null);
        horarioRepo.delete(buscado);
        Assertions.assertNull(horarioRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Horario guardado = horarioRepo.findById(1).orElse(null);
        guardado.setDia(18);
        Horario nuevo = horarioRepo.save(guardado);
        Assertions.assertEquals(nuevo.getDia(), 18);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Horario> buscado = horarioRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Horario> listaHorarios = horarioRepo.findAll();

        listaHorarios.forEach(System.out::println);
    }


}
