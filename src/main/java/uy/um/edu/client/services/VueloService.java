package uy.um.edu.client.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class VueloService {

    private final RestTemplate restTemplate;

    public VueloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void agregarVuelo(Vuelo vuelo) throws InvalidInformation, EntidadYaExiste {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL + "/vuelos", vuelo, String.class);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                return;
            } else if (response.getStatusCode() == HttpStatus.CONFLICT) {
                throw new EntidadYaExiste("Vuelo ya existe");
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new InvalidInformation("Información inválida");
            } else {
                throw new RuntimeException();
            }
        }catch (HttpClientErrorException.Conflict e){
            throw new EntidadYaExiste("Vuelo ya existe");
        }
        catch (HttpClientErrorException.BadRequest e){
            throw new InvalidInformation("Informacion invalida");
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void validarVuelo(Vuelo vuelo, Aeropuerto aeropuerto) throws InvalidInformation {
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL + "/vuelos/" + vuelo.getCodigoVuelo() + "/validar/" + aeropuerto.getCodigo(), aeropuerto, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return;
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new InvalidInformation("Información inválida");
            } else {
                throw new RuntimeException();
            }
        }catch (HttpClientErrorException.BadRequest e){
            throw new InvalidInformation("Información inválida");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }


    }

    public void rechazarVuelo(Vuelo item, Aeropuerto aeropuerto) throws InvalidInformation{
        ResponseEntity<String> response = restTemplate.postForEntity(baseURL + "/vuelos/" + item.getCodigoVuelo() + "/rechazar/" + aeropuerto.getCodigo(), aeropuerto, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        } else {
            throw new InvalidInformation("No se pudo rechazar el vuelo");
        }
    }
    public Vuelo obtenerVueloPorCodigo(String codigo) {
        ResponseEntity<Vuelo> response = restTemplate.getForEntity(baseURL + "/vuelos/" + codigo, Vuelo.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }
}
