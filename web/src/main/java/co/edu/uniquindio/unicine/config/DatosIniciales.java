package co.edu.uniquindio.unicine.config;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatosIniciales implements CommandLineRunner {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Override
    public void run(String... args) throws Exception {


        List<Ciudad> ciudades = adminServicio.listarCiudades();

        if (ciudades.isEmpty()){
            Ciudad c1 = Ciudad.builder().nombre("Pijao").build();
            Ciudad c2 = Ciudad.builder().nombre("Sevilla").build();
            adminServicio.crearCiudad(c1);
            adminServicio.crearCiudad(c2);


            adminServicio.registrarAdministradorTeatro(AdministradorTeatro.builder().cedula(12345).nombre("Admin Teatro 1").correo("adminT@email.com").password("1234").build());
            adminServicio.crearAdmin(Administrador.builder().cedula(1234).nombre("Super Admin").correo("admin@email.com").password("1234").build());
        }

        
    }
}
