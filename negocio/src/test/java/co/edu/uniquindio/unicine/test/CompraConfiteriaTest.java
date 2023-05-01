package co.edu.uniquindio.unicine.test;


import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.CompraConfiteriaRepo;
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
public class CompraConfiteriaTest {

    @Autowired
    private CompraConfiteriaRepo compraConfiteriaRepo;

   /* @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        MedioPago medioPago = MedioPago.TARJETA_UNICINE;


        String[] tels = new String[]{"231", "323"};
        Cliente cliente = new Cliente(12345, "pepito", "pepito@email.com", "1234", "url", Arrays.asList(tels));
        Horario horario = new Horario("LMXJVSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
        Genero[] generos = new Genero[]{Genero.TERROR};
        Pelicula pelicula = new Pelicula("El sismo", EstadoPelicula.CARTELERA,"De la naturaleza nadie se salva", "http:@sismo",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
        Funcion funcion = new Funcion(24000f, horario, sala, pelicula);
        Compra compra = new Compra(medioPago, cliente, funcion);
        compra.setValorTotal(50000f);

        Confiteria confiteria = new Confiteria("Salchipapa", 25000f, "url");

        CompraConfiteria compraConfiteria = new CompraConfiteria(30000f, 2, compra, confiteria);
        CompraConfiteria guardado = compraConfiteriaRepo.save(compraConfiteria);
        Assertions.assertEquals(guardado.getCompra().getCliente().getNombre(), "pepito");
    }*/
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {

        CompraConfiteria buscado = compraConfiteriaRepo.findById(1).orElse(null);
        compraConfiteriaRepo.delete(buscado);
        Assertions.assertNull(compraConfiteriaRepo.findById(1).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {

        CompraConfiteria guardado = compraConfiteriaRepo.findById(1).orElse(null);
        guardado.setUnidades(5);
        CompraConfiteria nuevo = compraConfiteriaRepo.save(guardado);
        Assertions.assertEquals(nuevo.getUnidades(), 5);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {

        Optional<CompraConfiteria> buscado = compraConfiteriaRepo.findById(1);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<CompraConfiteria> listaCompraConfiterias = compraConfiteriaRepo.findAll();

        listaCompraConfiterias.forEach(System.out::println);
    }
}
