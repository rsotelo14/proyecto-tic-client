package uy.um.edu.client.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.pasajeros.Pasajero;
import uy.um.edu.client.exceptions.EntidadYaExiste;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class PasajeroService{
    private final RestTemplate restTemplate;

    public PasajeroService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
    }
    public void agregarPasajero(Pasajero pasajero) throws EntidadYaExiste {
        ResponseEntity<String> response = restTemplate.postForEntity(baseURL+ "/usuarios/pasajero", pasajero, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        } else {
            throw new EntidadYaExiste("Pasajero ya existe");
        }
    }

}