package uy.um.edu.client.entities.vuelos;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.PuertaAeropuerto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelo {
    private Long id;
    private String codigoVuelo;
    private Aerolinea aerolinea;

    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private ReservaPista reservaPistaOrigen;
    private ReservaPista reservaPistaDestino;
    private ReservaPuerta reservaPuertaOrigen;
    private ReservaPuerta reservaPuertaDestino;

    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private LocalTime horaSalidaEstimada;
    private LocalTime horaSalidaReal;
    private LocalTime horaLlegadaEstimada;
    private LocalTime horaLlegadaReal;
    private Long capacidadMaxima;
    private Long pasajerosConfirmados;

    private EstadoVuelo estado;

    private Avion avion;

    public Vuelo() {
    }


    public Vuelo(String codigoVuelo ,Aerolinea aerolinea, Aeropuerto aeropuerto_origen,

                 Aeropuerto aeropuerto_destino,
                 LocalDate fecha_salida, LocalDate fecha_llegada, LocalTime hora_salida_estimada, LocalTime hora_salida_real,
                 LocalTime hora_llegada_estimada, LocalTime hora_llegada_real, Long capacidad_maxima,
                 Long pasajeros_confirmados, Avion avion, EstadoVuelo estado) {
        this.codigoVuelo=codigoVuelo;
        this.aerolinea = aerolinea;
        this.aeropuertoOrigen = aeropuerto_origen;
        this.aeropuertoDestino = aeropuerto_destino;
        this.fechaSalida = fecha_salida;
        this.fechaLlegada = fecha_llegada;
        this.horaSalidaEstimada = hora_salida_estimada;
        this.horaSalidaReal = hora_salida_real;
        this.horaLlegadaEstimada = hora_llegada_estimada;
        this.horaLlegadaReal = hora_llegada_real;

        this.capacidadMaxima = capacidad_maxima;
        this.pasajerosConfirmados = pasajeros_confirmados;
        this.avion = avion;
        this.estado= estado;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
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

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalTime getHoraSalidaEstimada() {
        return horaSalidaEstimada;
    }

    public void setHoraSalidaEstimada(LocalTime horaSalidaEstimada) {
        this.horaSalidaEstimada = horaSalidaEstimada;
    }

    public LocalTime getHoraSalidaReal() {
        return horaSalidaReal;
    }

    public void setHoraSalidaReal(LocalTime horaSalidaReal) {
        this.horaSalidaReal = horaSalidaReal;
    }

    public LocalTime getHoraLlegadaEstimada() {
        return horaLlegadaEstimada;
    }

    public void setHoraLlegadaEstimada(LocalTime horaLlegadaEstimada) {
        this.horaLlegadaEstimada = horaLlegadaEstimada;
    }

    public LocalTime getHoraLlegadaReal() {
        return horaLlegadaReal;
    }

    public void setHoraLlegadaReal(LocalTime horaLlegadaReal) {
        this.horaLlegadaReal = horaLlegadaReal;
    }


    public Long getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Long capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Long getPasajerosConfirmados() {
        return pasajerosConfirmados;
    }

    public void setPasajerosConfirmados(Long pasajerosConfirmados) {
        this.pasajerosConfirmados = pasajerosConfirmados;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVuelo estado) {
        this.estado = estado;
    }

    public ReservaPista getReservaPistaOrigen() {
        return reservaPistaOrigen;
    }

    public void setReservaPistaOrigen(ReservaPista reservaPistaOrigen) {
        this.reservaPistaOrigen = reservaPistaOrigen;
    }

    public ReservaPista getReservaPistaDestino() {
        return reservaPistaDestino;
    }

    public void setReservaPistaDestino(ReservaPista reservaPistaDestino) {
        this.reservaPistaDestino = reservaPistaDestino;
    }

    public ReservaPuerta getReservaPuertaOrigen() {
        return reservaPuertaOrigen;
    }

    public void setReservaPuertaOrigen(ReservaPuerta reservaPuertaOrigen) {
        this.reservaPuertaOrigen = reservaPuertaOrigen;
    }

    public ReservaPuerta getReservaPuertaDestino() {
        return reservaPuertaDestino;
    }

    public void setReservaPuertaDestino(ReservaPuerta reservaPuertaDestino) {
        this.reservaPuertaDestino = reservaPuertaDestino;
    }
}
