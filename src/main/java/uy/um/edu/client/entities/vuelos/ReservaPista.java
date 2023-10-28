package uy.um.edu.client.entities.vuelos;

import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.PistaAeropuerto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaPista {

    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;



    private PistaAeropuerto pista;

    private Vuelo vuelo;

    public void setPista(PistaAeropuerto value) {
        this.pista = value;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public PistaAeropuerto getPista() {
        return pista;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aeropuerto getAeropuerto() {
        return pista.getAeropuerto();
    }
}
