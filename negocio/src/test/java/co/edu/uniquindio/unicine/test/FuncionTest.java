package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
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
        Horario horario = new Horario("LMXJVSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
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

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculaNombreXFuncion() {

        String peliculaNombre = funcionRepo.obtenerPeliculaNombre(1);

        Assertions.assertEquals(peliculaNombre, "worldwest");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesxPelicula() {

        List<FuncionDTO> funciones = funcionRepo.listarFunciones(1);
        funciones.forEach(System.out::println);
        Assertions.assertEquals(funciones.size(), 1);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesSinCompras() {

        List<Funcion> funciones = funcionRepo.obtenerFuncionesSinCompras(1);
        funciones.forEach(System.out::println);
        Assertions.assertEquals(funciones.size(), 0);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesTeatro() {

        List<Funcion> funciones = funcionRepo.obtenerFuncionesTeatro(1, LocalDateTime.parse("2022-09-16T10:15:30"), LocalDateTime.parse("2022-10-06T10:15:30"));
        funciones.forEach(System.out::println);
        Assertions.assertEquals(funciones.size(), 5);
    }
}
