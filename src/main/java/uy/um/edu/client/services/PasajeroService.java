package uy.um.edu.client.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uy.um.edu.client.entities.pasajeros.Pasajero;
import uy.um.edu.client.entities.pasajeros.PasaporteCodigoVuelo;
import uy.um.edu.client.exceptions.EntidadNoExiste;
import uy.um.edu.client.exceptions.EntidadYaExiste;

import static uy.um.edu.client.config.RestTemplateConfig.baseURL;

@Service
public class PasajeroService{
    private final RestTemplate restTemplate;

    public PasajeroService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
    }
    public void agregarPasajero(Pasajero pasajero) throws EntidadYaExiste {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL+ "/usuarios/pasajero", pasajero, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return;
            } else {
                throw new EntidadYaExiste("Pasajero ya existe");
            }
        } catch (HttpClientErrorException e){
            throw new EntidadYaExiste("Pasajero ya existe");
        } catch (Exception e){
            throw new RuntimeException();
        }

    }
    public Pasajero  obtenerPasajeroPorPasaporte(String pasaporte) {
        ResponseEntity<Pasajero> response = restTemplate.getForEntity(baseURL + "/usuarios/pasajero/" + pasaporte, Pasajero.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }
    public void enviarPasaporteCodigoVuelo(PasaporteCodigoVuelo pasaporteCodigoVuelo) throws EntidadYaExiste, EntidadNoExiste {
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL+ "/vuelos/asignar-pasajero", pasaporteCodigoVuelo, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return;
            } else {
                throw new EntidadYaExiste("Pasajero ya existe");
            }
        } catch (HttpClientErrorException.Conflict e){
            throw new EntidadYaExiste("Pasajero ya existe");
        }catch (HttpClientErrorException.BadRequest e){
            throw new EntidadNoExiste("Pasajero no existe");
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void checkIn(PasaporteCodigoVuelo pasaporteCodigoVuelo) throws EntidadYaExiste, EntidadNoExiste {
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(baseURL+ "/vuelos/check-in", pasaporteCodigoVuelo, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return;
            } else {
                throw new EntidadYaExiste("Pasajero ya existe");
            }
        } catch (HttpClientErrorException.Conflict e){
            throw new EntidadYaExiste(e.getMessage());
        }catch (HttpClientErrorException.BadRequest e){
            throw new EntidadNoExiste(e.getMessage());
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}