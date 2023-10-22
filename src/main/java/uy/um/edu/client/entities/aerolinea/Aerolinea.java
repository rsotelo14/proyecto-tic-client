package uy.um.edu.client.entities.aerolinea;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.vuelos.Avion;
import uy.um.edu.client.entities.vuelos.Vuelo;

import java.util.ArrayList;
import java.util.List;



public class Aerolinea {

    private Long id;

    private String codigoIATA;

    private String codigoICAO;

    private String nombre;
    private String paisDeOrigen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @JsonIgnore
    private List<Aeropuerto> aeropuertos=new ArrayList<>();
    @JsonIgnore
    private List<Vuelo> vuelos=new ArrayList<>();
    @JsonIgnore
    private List<Avion> aviones=new ArrayList<>();

    public Aerolinea() {
    }
    @JsonIgnore
    private List<UsuarioAerolinea> usuarios=new ArrayList<>();

    public String getCodigoIATA() {
        return codigoIATA;
    }

    public void setCodigoIATA(String codigoIATA) {
        this.codigoIATA = codigoIATA;
    }

    public String getCodigoICAO() {
        return codigoICAO;
    }

    public void setCodigoICAO(String codigoICAO) {
        this.codigoICAO = codigoICAO;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
}
