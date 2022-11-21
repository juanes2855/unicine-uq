package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro, Integer> {

    @Query("select t from Teatro t where t.ciudad.nombre = :nombreCiudad")
    List<Teatro> listar(String nombreCiudad);

    @Query("select t.ciudad.codigo, t.ciudad.nombre, count(t) from Teatro t group by t.ciudad")
    List<Object[]> listarTeatros();
    @Query("select t from Teatro t  where t.ciudad.codigo= :ciudadCodigo")
    List<Teatro> listarTeatrosCiudad(Integer ciudadCodigo);
}
