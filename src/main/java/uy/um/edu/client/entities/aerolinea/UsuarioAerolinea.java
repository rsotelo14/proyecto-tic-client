package uy.um.edu.client.entities.aerolinea;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import uy.um.edu.client.entities.Usuario;

@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminAerolinea.class, name = "adminAerolinea"),
        @JsonSubTypes.Type(value = EmpleadoAerolinea.class, name = "empleadoAerolinea")
})
public class UsuarioAerolinea extends Usuario {

    private Aerolinea aerolinea;

    public UsuarioAerolinea(String nombre, String apellido, String email, String password, Aerolinea aerolinea) {
        super(nombre, apellido, email, password);
        this.aerolinea = aerolinea;
    }

    public UsuarioAerolinea() {
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
}
