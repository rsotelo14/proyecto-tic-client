package uy.um.edu.client.entities.aeropuerto;


import uy.um.edu.client.entities.vuelos.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class PuertaAeropuerto {
    private Long id;
    private Long tarifaHora;
    private Long numeroPuerta;
    private Aeropuerto aeropuerto;

    public PuertaAeropuerto() {
    }

    private List<Vuelo> vuelosSalientes =new ArrayList<>();
    private List<Vuelo> vuelosEntrantes =new ArrayList<>();

}
