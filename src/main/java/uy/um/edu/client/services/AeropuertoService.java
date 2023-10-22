package uy.um.edu.client.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.client.entities.vuelos.Vuelo;

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

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
    }

    public Iterable<UsuarioAeropuerto> obtenerUsuarios(Aeropuerto aeropuerto) {
        ResponseEntity<UsuarioAeropuerto[]> response = restTemplate.getForEntity(baseURL + "/aeropuertos/" + aeropuerto.getCodigo() + "/usuarios", UsuarioAeropuerto[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }
}
