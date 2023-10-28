package uy.um.edu.client.services;

import uy.um.edu.client.entities.vuelos.ReservaPista;
import uy.um.edu.client.entities.vuelos.ReservaPuerta;

public class ReservasDTO {
    private ReservaPista reservaPista;
    private ReservaPuerta reservaPuerta;

    public ReservasDTO() {
    }

    public ReservaPista getReservaPista() {
        return reservaPista;
    }

    public void setReservaPista(ReservaPista reservaPista) {
        this.reservaPista = reservaPista;
    }

    public ReservaPuerta getReservaPuerta() {
        return reservaPuerta;
    }

    public void setReservaPuerta(ReservaPuerta reservaPuerta) {
        this.reservaPuerta = reservaPuerta;
    }
}
