package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
    @Query("select c from Cliente c where c.correo = ?1")
    Cliente obtener (String correo);

    @Query("select c from Cliente c where c.cedula = :cedulaCLiente")
    Cliente obtenerPorCedula (Integer cedulaCLiente);

    Optional<Cliente> findByCedula(Integer cedula);

    Optional<Cliente> findByCorreo(String correo);

    @Query("select c from Cliente c where c.correo = :correo and c.password = :password")
    Cliente comprobarAutenticacion(String correo, String password);

    Cliente findByCorreoAndPassword(String correo,String password);

    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstado(boolean estado, Pageable paginador);
    @Query("select comp from Cliente cli, in (cli.compras) comp where cli.correo= :correo ")
    List<Compra> obtenerCompras (String correo);
    @Query("select comp from Cliente  cli, in (cli.compras) comp where cli.cedula= :cedula")
    List<Compra> obtenerCompras(Integer cedula);
    @Query("select cup.codigo_cupon from Cliente cli join cli.codigoCupon cup where cli.cedula = :cedula")
    List<Cupon> obtenerListaCupones(Integer cedula);
    @Query("select comp from Cliente cli join cli.compras comp")
    List<Compra> obtenerComprasXCliente();

    @Query("select cli.nombre, comp from Cliente cli left join cli.compras comp")
    List<Object[]> obtenerTodasLosClientemasCompras();
}
