package uy.um.edu.client.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.vuelos.ReservaPista;
import uy.um.edu.client.entities.vuelos.ReservaPuerta;
import uy.um.edu.client.exceptions.InvalidInformation;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class ReservasService {

    private final RestTemplate restTemplate;

    public ReservasService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void reservarPistaYPuerta(ReservaPista reservaPista, ReservaPuerta reservaPuerta) throws InvalidInformation {
        System.out.println("Aeropuerto Pista: " + reservaPista.getAeropuerto().getCodigo());
        System.out.println("Aeropuerto Puerta: " + reservaPuerta.getAeropuerto().getCodigo());
        ReservasDTO reservasDTO = new ReservasDTO();
        reservasDTO.setReservaPista(reservaPista);
        reservasDTO.setReservaPuerta(reservaPuerta);
        try
        {
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL + "/reservas", reservasDTO, String.class);
        }
        catch (HttpClientErrorException.BadRequest e){
            throw new InvalidInformation(e.getMessage());
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

}
