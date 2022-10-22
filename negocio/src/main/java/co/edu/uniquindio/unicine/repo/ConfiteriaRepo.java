package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria, Integer> {

    @Query("select  p from Confiteria p where p.nombre like concat('%', :nombre , '%')")
    List<Confiteria> buscarConfiteria(String nombre);

    @Query("select  p from Confiteria p where p.nombre= :nombre ")
    Optional<Confiteria> buscarConfiteriaxNombre(String nombre);
}
