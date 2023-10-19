package uy.um.edu.client.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.vuelos.Vuelo;

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
        return null;
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
    }
}
