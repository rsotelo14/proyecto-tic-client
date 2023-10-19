package uy.um.edu.client.entities.aerolinea;

import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.vuelos.Avion;
import uy.um.edu.client.entities.vuelos.Vuelo;

import java.util.ArrayList;
import java.util.List;



public class Aerolinea {

    private Long id;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
    }
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private List<Aeropuerto> aeropuertos=new ArrayList<>();
    private List<Vuelo> vuelos=new ArrayList<>();
    private List<Avion> aviones=new ArrayList<>();

    public Aerolinea() {
    }
    private List<UsuarioAerolinea> usuarios=new ArrayList<>();

//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
}
