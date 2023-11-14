package uy.um.edu.client.entities.vuelos;

import uy.um.edu.client.entities.pasajeros.Pasajero;

public class Asientos {
    private Long id;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private Boolean checkIn;
    private Boolean boarding;

    public Asientos() {
    }

    public Long getId() {
        return id;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }


    public Boolean getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getBoarding() {
        return boarding;
    }

    public void setBoarding(Boolean boarding) {
        this.boarding = boarding;
    }
}
