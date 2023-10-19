package uy.um.edu.client.entities.pasajeros;


import uy.um.edu.client.entities.Usuario;

public class Pasajero extends Usuario {
    public Pasajero(){

    }
    public Pasajero(String nombre, String apellido, String email, String password) {
        super(nombre, apellido, email, password);
    }

}
