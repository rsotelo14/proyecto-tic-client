package uy.um.edu.client.entities.pasajeros;


import uy.um.edu.client.entities.Usuario;

public class Pasajero extends Usuario {
    private String pasaporte;
    public Pasajero(){

    }
    public Pasajero(String nombre, String apellido, String email, String contraseña) {
        super(nombre, apellido, email, contraseña);
    }

    @Override
    public String getPasaporte() {
        return pasaporte;
    }

    @Override
    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }
}
