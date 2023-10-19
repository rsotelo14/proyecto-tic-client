package uy.um.edu.client.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.exceptions.EntidadYaExiste;

@Service
public class AerolineaService {
    private final RestTemplate restTemplate;

    public AerolineaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void agregarAerolinea(Aerolinea aerolinea) throws EntidadYaExiste {
    }
}
