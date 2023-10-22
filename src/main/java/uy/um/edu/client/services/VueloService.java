package uy.um.edu.client.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    }

    public void validarVuelo(Vuelo vuelo, Aeropuerto aeropuerto) throws InvalidInformation {
        ResponseEntity<Vuelo> response = restTemplate.postForEntity(baseURL + "/vuelos/" + vuelo.getCodigoVuelo() + "/validar/" + aeropuerto.getCodigo(), aeropuerto, Vuelo.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        } else {
            throw new InvalidInformation("No se pudo validar el vuelo");
        }

    }

    public void rechazarVuelo(Vuelo item, Aeropuerto aeropuerto) throws InvalidInformation{
        ResponseEntity<Vuelo> response = restTemplate.postForEntity(baseURL + "/vuelos/" + item.getCodigoVuelo() + "/rechazar/" + aeropuerto.getCodigo(), aeropuerto, Vuelo.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        } else {
            throw new InvalidInformation("No se pudo rechazar el vuelo");
        }
    }
}
