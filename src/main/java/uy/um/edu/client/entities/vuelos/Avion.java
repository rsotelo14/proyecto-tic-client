
package uy.um.edu.client.entities.vuelos;


import uy.um.edu.client.entities.aerolinea.Aerolinea;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Avion {
    private Long id;
    private Aerolinea aerolinea;
    private String codigo;
    private String nombre;
    private Long capacidad;
    private String tipoAvion;
    @JsonIgnore
    private List<Vuelo> vuelos;

    public Avion(Long id, Aerolinea aerolinea, String nombre, Long capacidad, String tipoAvion, List<Vuelo> vuelos) {
        this.id = id;
        this.aerolinea = aerolinea;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoAvion = tipoAvion;
        this.vuelos = vuelos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Long capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(String tipoAvion) {
        this.tipoAvion = tipoAvion;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public Avion() {
    }

    @Override
    public String toString() {
        return "Avion{" +
                "codigo='" + codigo + '\'' +
                '}';
    }
}
