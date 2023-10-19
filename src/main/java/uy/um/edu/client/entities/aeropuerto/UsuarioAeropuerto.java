package uy.um.edu.client.entities.aeropuerto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import uy.um.edu.client.entities.Usuario;

@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminAeropuerto.class),
        @JsonSubTypes.Type(value = MaleteroAeropuerto.class)
})
public class UsuarioAeropuerto extends Usuario {

    private Aeropuerto aeropuerto;

    public UsuarioAeropuerto(String nombre, String apellido, String email, String password, Aeropuerto aeropuerto) {
        super(nombre, apellido, email, password);
        this.aeropuerto = aeropuerto;
    }

    public UsuarioAeropuerto() {
    }
    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}
