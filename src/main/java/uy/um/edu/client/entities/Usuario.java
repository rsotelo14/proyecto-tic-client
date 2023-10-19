package uy.um.edu.client.entities;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import uy.um.edu.client.entities.aerolinea.UsuarioAerolinea;
import uy.um.edu.client.entities.aeropuerto.UsuarioAeropuerto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UsuarioAerolinea.class),
        @JsonSubTypes.Type(value = UsuarioAeropuerto.class),
        @JsonSubTypes.Type(value = SuperUser.class)
})
public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;

    private String contrasena;
    private String pasaporte;

    public Usuario() {
    }
    public Usuario(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = email;
        this.contrasena = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Getters y setters

}