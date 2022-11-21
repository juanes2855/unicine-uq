package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, Integer> {

    @Query("select c from AdministradorTeatro c where c.correo = :correo and c.password = :password ")
    AdministradorTeatro comprobarAutenticacion(String correo, String password);

    Optional<AdministradorTeatro> findByCorreoAndPassword(String correo, String password);

    Optional<AdministradorTeatro> findByCorreo(String correo);
}
