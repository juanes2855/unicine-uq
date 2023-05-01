package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdminServicioTest {

    @Autowired
    private AdminServicio adminServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCiudadTest() {

        Ciudad ciudad = Ciudad.builder().nombre("Popayan").build();
        ciudad.setCodigo(8);
        try {
            Ciudad nuevo = adminServicio.crearCiudad(ciudad);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* @Test
    @Sql("classpath:dataset.sql")
    public void registrarPeliculaTest() {
        Genero[] generos = new Genero[]{Genero.TERROR};
        EstadoPelicula estadoPelicula = EstadoPelicula.CARTELERA;
        Pelicula pelicula = new Pelicula("El sismo", estadoPelicula,"De la naturaleza nadie se salva", "http:@sismo",
                "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
        pelicula.setCodigo(8);
        try {
            Pelicula nuevo = adminServicio.crearPelicula(pelicula);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }*/

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPeliculaTest() {

        try {
            Pelicula pelicula = adminServicio.obtenerPelicula(1);
            pelicula.setNombre("Rapido y furioso");
            Pelicula nuevo = adminServicio.actualizarPelicula(pelicula);
            Assertions.assertEquals("Rapido y furioso", nuevo.getNombre());
        } catch (Exception e) {
             throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPeliculaTest(){

        try {
            adminServicio.eliminarPelicula(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
           // Pelicula pelicula = adminServicio.obtenerPelicula(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculaTest(){
        List<Pelicula> lista = adminServicio.listarPeliculas();
        lista.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCuponTest() {

        Cupon cupon = new Cupon("Descuento 10%",0.10f, LocalDateTime.parse("2007-12-03T10:15:30"));
        cupon.setCodigo(8);
        try {
            Cupon nuevo = adminServicio.crearCupon(cupon);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCuponTest() {

        try {
            Cupon cupon= adminServicio.obtenerCupon(1);
            cupon.setDescripcion("descuento 20%");
            Cupon nuevo = adminServicio.actualizarCupon(cupon);
            Assertions.assertEquals("descuento 20%", nuevo.getDescripcion());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuponTest(){

        try {
            adminServicio.eliminarCupon(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
            // Cupon cupon= adminServicio.obtenerCupon(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponTest(){
        List<Cupon> lista = adminServicio.listarCupones();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void autenticacionAdminTest() {
       try {
           Administrador administrador = adminServicio.login("jf@uqvirtual.co", "12345");
           Assertions.assertNotNull(administrador);
       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarAdministradorTeatroTest() {
        String[] tels = new String[]{"231", "323"};
        AdministradorTeatro administradorTeatro = new AdministradorTeatro(12345, "pepito", "pepito@email.com", "1234");
        try {
            AdministradorTeatro nuevo = adminServicio.registrarAdministradorTeatro(administradorTeatro);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministradorTeatroTest() {

        try {
            AdministradorTeatro administradorTeatro= adminServicio.obtenerAdministradorTeatro(1091899);
            administradorTeatro.setNombre("Carlos");
            AdministradorTeatro nuevo = adminServicio.actualizarAdministradorTeatro(administradorTeatro);
            Assertions.assertEquals("Carlos", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministradorTeatroTest(){

        try {
            adminServicio.eliminarAdministradorTeatro(1091899);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
            // AdministradorTeatro administradorTeatro= adminServicio.obtenerAdministradorTeatro(1091899);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradorTeatroTest(){
        List<AdministradorTeatro> lista = adminServicio.listarAdministradorTeatro();
        lista.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarConfiteriaTest() {
        Confiteria confiteria = new Confiteria("Salchipapa", 25000f, "url");
        confiteria.setCodigo(12);
        try {
            Confiteria nuevo = adminServicio.crearConfiteria(confiteria);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarConfiteriaTest() {

        try {
            Confiteria confiteria= adminServicio.obtenerConfiteria(1);
            confiteria.setNombre("Arroz chino");
            Confiteria nuevo = adminServicio.actualizarConfiteria(confiteria);
            Assertions.assertEquals("Arroz chino", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarConfiteriaTest(){

        try {
            adminServicio.eliminarConfiteria(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
            // Confiteria confiteria= adminServicio.obtenerConfiteria(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteriaTest(){
        List<Confiteria> lista = adminServicio.listarConfiterias();
        lista.forEach(System.out::println);
    }
}