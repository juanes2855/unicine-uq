package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repo.EntradaRepo;
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
public class EntradaTest {

    @Autowired
    private EntradaRepo entradaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        Entrada entrada = new Entrada(15000f, 2, 4);
        Entrada guardado = entradaRepo.save(entrada);
        Assertions.assertEquals(guardado.getFila(), 2);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Entrada buscado = entradaRepo.findById(1).orElse(null);
        entradaRepo.delete(buscado);
        Assertions.assertNull(entradaRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Entrada guardado = entradaRepo.findById(1).orElse(null);
        guardado.setColumna(6);
        Entrada nuevo = entradaRepo.save(guardado);
        Assertions.assertEquals(nuevo.getColumna(), 6);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Entrada> buscado = entradaRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Entrada> listaEntradas = entradaRepo.findAll();

        listaEntradas.forEach(System.out::println);
    }
}
