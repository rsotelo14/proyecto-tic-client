package uy.um.edu.client.entities.vuelos;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.PuertaAeropuerto;

public class Vuelo {
    private Long id;
    private String codigoVuelo;
    private Aerolinea aerolinea;

    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private PuertaAeropuerto puertaOrigen;

    private PuertaAeropuerto puertaDestino;

    private String fechaSalida;
    private String fechaLlegada;
    private String horaSalidaEstimada;
    private String horaSalidaReal;
    private String horaLlegadaEstimada;
    private String horaLlegadaReal;
    private Long capacidadMaxima;
    private Long pasajerosConfirmados;

    private EstadoVuelo estado;

    private Avion avion;

    public Vuelo() {
    }


    public Vuelo(String codigoVuelo ,Aerolinea aerolinea, Aeropuerto aeropuerto_origen,

                 Aeropuerto aeropuerto_destino,
                 String fecha_salida, String fecha_llegada, String hora_salida_estimada, String hora_salida_real,
                 String hora_llegada_estimada, String hora_llegada_real, Long capacidad_maxima,
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

    public PuertaAeropuerto getPuertaOrigen() {
        return puertaOrigen;
    }

    public void setPuertaOrigen(PuertaAeropuerto puertaOrigen) {
        this.puertaOrigen = puertaOrigen;
    }

    public PuertaAeropuerto getPuertaDestino() {
        return puertaDestino;
    }

    public void setPuertaDestino(PuertaAeropuerto puertaDestino) {
        this.puertaDestino = puertaDestino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getHoraSalidaEstimada() {
        return horaSalidaEstimada;
    }

    public void setHoraSalidaEstimada(String horaSalidaEstimada) {
        this.horaSalidaEstimada = horaSalidaEstimada;
    }

    public String getHoraSalidaReal() {
        return horaSalidaReal;
    }

    public void setHoraSalidaReal(String horaSalidaReal) {
        this.horaSalidaReal = horaSalidaReal;
    }

    public String getHoraLlegadaEstimada() {
        return horaLlegadaEstimada;
    }

    public void setHoraLlegadaEstimada(String horaLlegadaEstimada) {
        this.horaLlegadaEstimada = horaLlegadaEstimada;
    }

    public String getHoraLlegadaReal() {
        return horaLlegadaReal;
    }

    public void setHoraLlegadaReal(String horaLlegadaReal) {
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
}
