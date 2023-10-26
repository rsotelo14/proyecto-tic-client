package uy.um.edu.client.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.exceptions.EntidadYaExiste;

import java.util.List;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class AerolineaService {
    private final RestTemplate restTemplate;

    public AerolineaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void agregarAerolinea(Aerolinea aerolinea) throws EntidadYaExiste {
        ResponseEntity<Aerolinea> response = restTemplate.postForEntity(baseURL + "/aerolineas", aerolinea, Aerolinea.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        } else {
            throw new EntidadYaExiste("Aerolinea ya existe");
        }
    }
    public List<Aeropuerto> obtenerAeropuertosAsociados(Aerolinea aerolinea){
        ResponseEntity<Aeropuerto[]> response = restTemplate.getForEntity(baseURL + "/aerolineas/" + aerolinea.getCodigoIATA() + "/aeropuertos-asociados", Aeropuerto[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }

}
