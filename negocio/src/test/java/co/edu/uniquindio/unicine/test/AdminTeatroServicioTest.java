package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Transactional
@SpringBootTest
public class AdminTeatroServicioTest {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;


    @Test
    public void validarHoraTest() throws Exception {
        System.out.println( adminTeatroServicio.validarHora("15:00"));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarTeatroTest() {

        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
        try {
            Teatro nuevo = adminTeatroServicio.crearTeatro(teatro);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatroTest() {

        try {
            Teatro teatro= adminTeatroServicio.obtenerTeatro(1);
            teatro.setTelefono("7500000");
            Teatro nuevo = adminTeatroServicio.actualizarTeatro(teatro);
            Assertions.assertEquals("7500000", nuevo.getTelefono());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatroTest(){

        try {
            adminTeatroServicio.eliminarTeatro(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
          //   Teatro teatro= adminTeatroServicio.obtenerTeatro(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatroTest(){
        List<Teatro> lista = adminTeatroServicio.listarTeatros();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHorarioTest() {

        Horario horario = new Horario("VSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));

        try {
            Horario nuevo = adminTeatroServicio.crearHorario(horario);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHorarioTest(){

        try {
            adminTeatroServicio.eliminarHorario(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
            //   Horario horario= HorarioServicio.obtenerHorario(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorarioTest(){
        List<Horario> lista = adminTeatroServicio.listarHorarios();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSalaTest() {
        try {
        Ciudad ciudad = new Ciudad("Barrancabermeja");
        Ciudad nuevaCiudad = adminServicio.crearCiudad(ciudad);
        Teatro teatro = new Teatro("mi casa", "1231232", nuevaCiudad);
        Teatro nuevoTeatro = adminTeatroServicio.crearTeatro(teatro);
        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        DistribucionSillas nuevaDistribucion = adminTeatroServicio.registrarDistribucionSilla(distribucionSillas);
        Sala sala = new Sala("Cine xd", nuevoTeatro, nuevaDistribucion);

            Sala nuevo = adminTeatroServicio.crearSala(sala);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSalaTest() {

        try {
            Sala sala= adminTeatroServicio.obtenerSala(1);
            sala.setNombre("Sala VIP");
            Sala nuevo = adminTeatroServicio.actualizarSala(sala);
            Assertions.assertEquals("Sala VIP", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSalaTest(){

        try {
            adminTeatroServicio.eliminarSala(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
             // Sala sala= adminTeatroServicio.obtenerSala(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalaTest(){
        List<Sala> lista = adminTeatroServicio.listarSalas();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarDistribucionTest() {

        DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
        try {
            DistribucionSillas nuevo = adminTeatroServicio.registrarDistribucionSilla(distribucionSillas);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarDistribucionTest(){

        try {
            adminTeatroServicio.eliminarDistribucionSillas(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
            //DistribucionSillas distribucionSillas= adminTeatroServicio.obtenerDistribucionSillas(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarDistribucionTest() {
        List<DistribucionSillas> listaDistribuciones = adminTeatroServicio.listarDistribuciones();
        listaDistribuciones.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public  void  obtenerDistribucionTest() throws Exception {
        DistribucionSillas obtener = adminTeatroServicio.obtenerDistribucionSillas(1);
        Assertions.assertNotNull(obtener);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarFuncionTest() {
        try {
            Horario horario = new Horario("LMXJVSD", "15:30", LocalDateTime.of(2022, 10, 16, 15, 30, 0, 0), LocalDateTime.of(2022, 10, 31, 15, 30, 0, 0));
            Genero[] generos = new Genero[]{Genero.TERROR};
            Pelicula pelicula = new Pelicula("El sismo", EstadoPelicula.CARTELERA,"De la naturaleza nadie se salva", "http:@sismo",
                    "http:@Elsismo.jpj", "Andres Lopez, Esteban Henao", Arrays.asList(generos));
            Ciudad ciudad = new Ciudad("Barrancabermeja");
            Teatro teatro = new Teatro("mi casa", "1231232", ciudad);
            DistribucionSillas distribucionSillas = new DistribucionSillas(4,4);
            Sala sala = new Sala("Cine xd", teatro, distribucionSillas);
            Funcion funcion = new Funcion(24000f, horario, sala, pelicula);
            Funcion nuevo = adminTeatroServicio.crearFuncion(funcion);

            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncionTest() {

        try {
            Funcion funcion= adminTeatroServicio.obtenerFuncion(1);
            funcion.setPrecio(30000f);
            Funcion nuevo = adminTeatroServicio.actualizarFuncion(funcion);
            Assertions.assertEquals(30000f, nuevo.getPrecio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncionTest(){

        try {
            adminTeatroServicio.eliminarFuncion(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }

        try{
             //Funcion funcion= adminTeatroServicio.obtenerFuncion(1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesTest() {
        List<Funcion> listaFunciones = adminTeatroServicio.listarFunciones();
        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void autenticacionAdminTest() {
        try {
            AdministradorTeatro administrador = adminTeatroServicio.login("jfmd@uqvirtual.co", "12345a");
            Assertions.assertNotNull(administrador);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
