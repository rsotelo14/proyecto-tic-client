package uy.um.edu.client.entities.aeropuerto;

public class PistaAeropuerto {

    private Long id;
    private Long tarifaHora;


    private Long numeroPista;

    private Aeropuerto aeropuerto;

    public PistaAeropuerto() {
    }

    public PistaAeropuerto(Long tarifaHora, Long numeroPista, Aeropuerto aeropuerto) {
        this.tarifaHora = tarifaHora;
        this.numeroPista = numeroPista;
        this.aeropuerto = aeropuerto;
    }

    public Long getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Long tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Long getNumeroPista() {
        return numeroPista;
    }

    public void setNumeroPista(Long numeroPista) {
        this.numeroPista = numeroPista;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    @Override
    public String toString() {
        if (this == null) return " ";
        return numeroPista.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
