package uy.um.edu.client.entities.pasajeros;

public class PasaporteCodigoVuelo {
    private String pasaporte;
    private String codigoVuelo;

    private Integer cantidadValijas;
    public PasaporteCodigoVuelo() {
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public void setCantidadValijas(Integer value) {
        this.cantidadValijas = value;
    }

    public Integer getCantidadValijas() {
        return cantidadValijas;
    }
}
