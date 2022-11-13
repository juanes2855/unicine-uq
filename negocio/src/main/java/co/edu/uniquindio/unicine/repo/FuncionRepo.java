package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre from Funcion f where f.codigo = :codigoFuncion")
    String obtenerPeliculaNombre (Integer codigoFuncion);

    @Query("select new co.edu.uniquindio.unicine.dto.FuncionDTO( f.pelicula.nombre, f.pelicula.estado, f.pelicula.urlImagen, " +
            "f.sala.codigo, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombre, f.horario) from Funcion f where f.pelicula.codigo = :codigoPelicula")
    List<FuncionDTO> listarFunciones (Integer codigoPelicula);
    @Query("select f from Funcion f where f.sala.teatro.codigo= :codigoTeatro and f.compras is empty")
    List<Funcion> obtenerFuncionesSinCompras(Integer codigoTeatro);

    @Query("select f from Funcion f where f.sala.teatro.codigo= :codigoTeatro and f.horario.fechaInicio < :fechaFin or f.horario.fechaFin > :fechaInicio")
    List<Funcion> obtenerFuncionesTeatro (Integer codigoTeatro, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("select f from Funcion f where f.horario= :horario and f.sala.codigo= :codigoSala") //ojo con horario
    List<Funcion> obtenerFuncionesSala(Horario horario, Integer codigoSala);

    @Query("select f from Funcion f where f.sala.teatro.ciudad.codigo= :codigoCiudad")
    List<Funcion> listarFuncionesCiudad(Integer codigoCiudad);

    @Query("select f from Funcion f where f.sala.teatro.codigo= :codigoTeatro")
    List<Funcion> listarFuncionesTeatro(Integer codigoTeatro);

    @Query("select distinct p from Pelicula p where p.estado= :estadoPelicula") // cambie funcion por pelicula antes peliculas por funcion
    List<Pelicula> buscarPeliculaEstado(EstadoPelicula estadoPelicula);

    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.codigo= :codigoCiudad and f.pelicula.estado= :estadoPelicula")
    List<Pelicula> buscarPeliculaEstadoCiudad(EstadoPelicula estadoPelicula, Integer codigoCiudad);
}
