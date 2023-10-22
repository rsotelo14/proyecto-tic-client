package uy.um.edu.client.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.config.RestTemplateConfig;
import uy.um.edu.client.entities.Usuario;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.client.exceptions.EntidadNoExiste;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;

import java.util.Arrays;
import java.util.List;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class UsuarioService {
    private RestTemplate restTemplate;

    public UsuarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Usuario> obtenerTodos() {
        Usuario[] usuarios = restTemplate.getForObject(baseURL+ "/usuarios", Usuario[].class);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }
        return Arrays.asList(usuarios);
    }

    public void agregarUsuario(Usuario usuario) throws InvalidInformation, EntidadYaExiste {
        ResponseEntity<?> response = restTemplate.postForEntity(baseURL +"/usuarios", usuario, String.class);
        if (response.getStatusCode() == HttpStatus.CREATED){
            return;
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new InvalidInformation("Informacion invalida");
        } else if (response.getStatusCode() == HttpStatus.CONFLICT) {
            throw new EntidadYaExiste("El usuario ya existe");
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new RuntimeException();
        }
    }

    public Usuario obtenerUnoPorCorreo(String correoAeropuerto) {
        ResponseEntity<Usuario> response = restTemplate.getForEntity(baseURL + "/usuarios/" + correoAeropuerto, Usuario.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return null;
        } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new RuntimeException();
        }
        return null;

    }

}
