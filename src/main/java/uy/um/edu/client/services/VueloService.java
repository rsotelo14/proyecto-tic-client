package uy.um.edu.client.services;

import org.springframework.stereotype.Service;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;

@Service
public class VueloService {
    public void agregarVuelo(Vuelo vuelo) throws InvalidInformation, EntidadYaExiste {
    }

    public void validarVuelo(Vuelo item, Aeropuerto aeropuerto) throws InvalidInformation {
    }

    public void rechazarVuelo(Vuelo item, Aeropuerto aeropuerto) throws InvalidInformation{
    }
}
