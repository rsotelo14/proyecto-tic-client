package uy.um.edu.client.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.vuelos.Avion;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import java.util.List;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;
@Service
public class AvionService {
    private final RestTemplate restTemplate;

    public AvionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void agregarAvion(Avion avion) throws EntidadYaExiste{
        ResponseEntity<Avion> response = restTemplate.postForEntity(baseURL+ "/avion", avion, Avion.class); //puede estar mal escrito
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        } else {
            throw new EntidadYaExiste("Avion ya existe");
        }
    }
    public List<Avion> obtenerAvionesAerolinea(Aerolinea aerolinea) {
        ResponseEntity<Avion[]> response = restTemplate.getForEntity(baseURL + "/aerolineas/" + aerolinea.getCodigoIATA() + "/aviones", Avion[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException();
        }
    }
}
