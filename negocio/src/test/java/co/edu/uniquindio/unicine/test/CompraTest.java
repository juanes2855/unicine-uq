package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.CompraRepo;
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
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {
        MedioPago medioPago = MedioPago.TARJETA_UNICINE;


        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "pepito@email.com", "1234", "url", Arrays.asList(tels));

        Horario horario = new Horario("LMXJVSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
        Genero[] generos = new Genero[]{Genero.TERROR};
        Pelicula pelicula = new Pelicula("El sismo", "En cartelera","De la naturaleza nadie se salva", "http:@sismo",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
        Funcion funcion = new Funcion(24000f, horario, sala, pelicula);



        Compra compra = new Compra(medioPago, cliente, funcion);
        compra.setValorTotal(50000f);
        Compra guardado = compraRepo.save(compra);
        Assertions.assertEquals(guardado.getCliente().getNombre(), "pepito");

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        Compra buscado = compraRepo.findById(1).orElse(null);
        compraRepo.delete(buscado);
        Assertions.assertNull(compraRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        Compra guardado = compraRepo.findById(1).orElse(null);
        guardado.getCliente().setNombre("Julian");
        Compra nuevo = compraRepo.save(guardado);
        Assertions.assertEquals(nuevo.getCliente().getNombre(), "Julian");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<Compra> buscado = compraRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Compra> listaCompras = compraRepo.findAll();

        Assertions.assertEquals(5, listaCompras.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarEntradas() {

        List<Entrada> listaEntradas = compraRepo.listarEntradas(1);
        Assertions.assertEquals(1, listaEntradas.size());
    }





}
