package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
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
public class FuncionTest {

    @Autowired
    private FuncionRepo funcionRepo;
    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        Horario horario = new Horario(17, "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
        Genero[] generos = new Genero[]{Genero.TERROR};
        Pelicula pelicula = new Pelicula("El sismo", "En cartelera","De la naturaleza nadie se salva", "http:@sismo",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
        Funcion funcion = new Funcion(24000f, horario, sala, pelicula);
        Funcion guardado = funcionRepo.save(funcion);
        Assertions.assertEquals(guardado.getPrecio(), 24000f);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Funcion buscado = funcionRepo.findById(1).orElse(null);
        funcionRepo.delete(buscado);
        Assertions.assertNull(funcionRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Funcion guardado = funcionRepo.findById(1).orElse(null);
        guardado.setPrecio(24900f);
        Funcion nuevo = funcionRepo.save(guardado);
        Assertions.assertEquals(nuevo.getPrecio(), 24900f);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Funcion> buscado = funcionRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Funcion> listaFunciones = funcionRepo.findAll();

        listaFunciones.forEach(System.out::println);
    }

}
