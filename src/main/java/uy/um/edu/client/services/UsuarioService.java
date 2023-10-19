package uy.um.edu.client.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.Usuario;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;

import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioService {
    private RestTemplate restTemplate;

    public UsuarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Usuario> obtenerTodos() {
        Usuario[] usuarios = restTemplate.getForObject("http://localhost:8080/usuarios", Usuario[].class);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }
        return Arrays.asList(usuarios);
    }

    public void agregarUsuario(Usuario usuario) throws InvalidInformation, EntidadYaExiste {
    }

    public Usuario obtenerUnoPorCorreo(String correoAeropuerto) {
        return null;
    }
}
