package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
    @Query("select c from Cliente c where c.correo = ?1")
    Cliente obtener (String correo);

    Cliente findByCorreo(String correo);

    @Query("select c from Cliente c where c.correo = :correo and c.password = :password")
    Cliente comprobarAutenticacion(String correo, String password);

    Cliente findByCorreoAndPassword(String correo,String password);

    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstado(boolean estado, Pageable paginador);
}
