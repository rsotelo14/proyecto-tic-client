package uy.um.edu.client.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.PistaAeropuerto;
import uy.um.edu.client.entities.aeropuerto.PuertaAeropuerto;
import uy.um.edu.client.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;

import java.util.List;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class AeropuertoService {
    private final RestTemplate restTemplate;

    public AeropuertoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Aeropuerto obtenerUnoPorCodigo(String codigoAeropuertoOrigen) {
        return null;
    }

    public Iterable<Vuelo> obtenerVuelosPendientes(Aeropuerto aeropuerto) {
        ResponseEntity<Vuelo[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/vuelos-pendientes", Vuelo[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) throws EntidadYaExiste, InvalidInformation {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL + "/aeropuertos", aeropuerto, String.class);
            return;
        } catch (HttpClientErrorException.Conflict e) {
            throw new EntidadYaExiste("Aeropuerto ya existe");
        } catch (HttpClientErrorException.BadRequest e) {
            throw new InvalidInformation("Informacion invalida");
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public Iterable<UsuarioAeropuerto> obtenerUsuarios(Aeropuerto aeropuerto) {
        ResponseEntity<UsuarioAeropuerto[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/usuarios", UsuarioAeropuerto[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }

    public List<Aeropuerto> obtenerAeropuertosPorAerolinea(Aerolinea aerolinea) {
        ResponseEntity<Aeropuerto[]> response = restTemplate.getForEntity(baseURL + "/aerolineas/" + aerolinea.getCodigoIATA() + "/aeropuertos", Aeropuerto[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }

    public List<Aerolinea> obtenerAerolineasDisponibles(Aeropuerto aeropuerto) {
        ResponseEntity<Aerolinea[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/aerolineas-disponibles", Aerolinea[].class);
        //System.out.println(response.getBody());
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());

        } else {
            throw new RuntimeException();
        }
    }
    public List<Aerolinea> obtenerAerolineasAsociadas(Aeropuerto aeropuerto) {
        ResponseEntity<Aerolinea[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/aerolineas-asociadas", Aerolinea[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());

        } else {
            throw new RuntimeException();
        }
    }


    public void asociarAerolinea(Aeropuerto aeropuerto, Aerolinea aerolinea) throws InvalidInformation {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/asociar-aerolinea/" + aerolinea.getCodigoIATA(), aerolinea, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {

                return;
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {

                throw new InvalidInformation("Información inválida");
            } else {

                throw new RuntimeException();
            }
        } catch (HttpClientErrorException.BadRequest e) {

            throw new InvalidInformation("Información inválida");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public List<PuertaAeropuerto> obtenerPuertas(Aeropuerto aeropuerto) {
        ResponseEntity<PuertaAeropuerto[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/puertas", PuertaAeropuerto[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }

    public List<PistaAeropuerto> obtenerPistas(Aeropuerto aeropuerto) {
        ResponseEntity<PistaAeropuerto[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/pistas", PistaAeropuerto[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }
}
