package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {


    private ClienteRepo clienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public Cliente obtenerCliente(Integer cedulaCliente) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findByCedula(cedulaCliente);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
         return guardado.get();
    }

    @Override
    public Cliente login(String correo, String password) throws Exception {
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if (cliente == null) {
            throw new Exception("Los datos de autenticación son incorrectos");
        }

        return cliente;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        boolean correoExiste = esRepetido(cliente.getCorreo());
        boolean cedulaExiste = esRepetido(cliente.getCorreo());

        if (cedulaExiste) {
            throw new Exception("La cédula ya está registrada");
        }

        if (correoExiste) {
            throw new Exception("El correo ya está en uso");
        }
        return clienteRepo.save(cliente);
    }

    private boolean esRepetido(String correo) {
        //Si es null retornar False
        return clienteRepo.findByCorreo(correo).orElse(null) != null;
    }
    private boolean esCedulaRepetida(Integer cedula) {
       return clienteRepo.findByCedula(cedula).orElse(null) != null;
    }
    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception{

        Optional<Cliente> guardado = clienteRepo.findById(cliente.getCedula());

        if (guardado.isEmpty()){
            throw new Exception("El cliente no existe");
        }

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer cedulaCliente) throws Exception{
        Optional<Cliente> guardado = clienteRepo.findById(cedulaCliente);

        if (guardado.isEmpty()){
            throw new Exception("El cliente no existe");
        }

      clienteRepo.delete(guardado.get());
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarHistorial(Integer cedulaCliente) throws Exception{
        return null;
    }

    @Override
    public Compra hacerCompra(Compra compra) throws Exception{
        return null;
    }

    @Override
    public boolean redimirCupon(Integer codigoCupon) throws Exception{
        return false;
    }
}
