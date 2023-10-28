package uy.um.edu.client.entities.aeropuerto;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Long getNumeroPuerta() {
        return numeroPuerta;
    }

    @JsonIgnore
    private List<Vuelo> vuelosSalientes =new ArrayList<>();
    @JsonIgnore
    private List<Vuelo> vuelosEntrantes =new ArrayList<>();

    @Override
    public String toString() {
        if (this == null){
            return " ";
        }
        return numeroPuerta.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Long tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public void setNumeroPuerta(Long numeroPuerta) {
        this.numeroPuerta = numeroPuerta;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}
