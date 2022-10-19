package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.Funcion;
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
}
