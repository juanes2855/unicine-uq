package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaTest {

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        Genero[] generos = new Genero[]{Genero.TERROR};
        Pelicula pelicula = new Pelicula("El sismo", "En cartelera","De la naturaleza nadie se salva", "http:@sismo",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        Pelicula guardado = peliculaRepo.save(pelicula);
        Assertions.assertEquals(guardado.getNombre(), "El sismo");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Pelicula buscado = peliculaRepo.findById(1).orElse(null);
        peliculaRepo.delete(buscado);
        Assertions.assertNull(peliculaRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Pelicula guardado = peliculaRepo.findById(1).orElse(null);
        guardado.setNombre("Monster inc");
        Pelicula nuevo = peliculaRepo.save(guardado);
        Assertions.assertEquals(nuevo.getNombre(), "Monster inc");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Pelicula> buscado = peliculaRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Pelicula> listaPeliculas = peliculaRepo.findAll();

        listaPeliculas.forEach(System.out::println);
    }

}
