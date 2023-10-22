package uy.um.edu.client.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL +"/usuarios", usuario, String.class);
            return;
        }catch (HttpClientErrorException.Conflict e){
            throw new EntidadYaExiste("Usuario ya existe");
        }
        catch (HttpClientErrorException.BadRequest e){
            throw new InvalidInformation("Informacion invalida");
        }
        catch (Exception e){
            throw new RuntimeException();
        }

    }

    public Usuario obtenerUnoPorCorreo(String correoAeropuerto) {
        try {
            ResponseEntity<Usuario> response = restTemplate.getForEntity(baseURL + "/usuarios/" + correoAeropuerto, Usuario.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new RuntimeException("Unexpected response status: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (HttpClientErrorException e) {
            // Puedes manejar otros errores 4xx aquí si lo necesitas
            throw new RuntimeException("Client error: " + e.getStatusCode());
        } catch (HttpServerErrorException e) {
            // Errores del servidor (5xx)
            throw new RuntimeException("Server error: " + e.getStatusCode());
        }

    }

}
