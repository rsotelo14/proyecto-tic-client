package uy.um.edu.client.entities.vuelos;

import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.PuertaAeropuerto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaPuerta {

    private Long id;

    private LocalDate fecha;

    private LocalTime horaInicio;
    private LocalTime horaFin;


    private PuertaAeropuerto puerta;

    private Vuelo vuelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public PuertaAeropuerto getPuerta() {
        return puerta;
    }

    public void setPuerta(PuertaAeropuerto puerta) {
        this.puerta = puerta;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Aeropuerto getAeropuerto() {
        return puerta.getAeropuerto();
    }
}
