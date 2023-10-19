package uy.um.edu.client.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioAerolineaService {
    private final RestTemplate restTemplate;

    public UsuarioAerolineaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
