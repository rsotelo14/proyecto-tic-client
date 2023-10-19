package uy.um.edu.client.entities;



public class SuperUser extends Usuario{

    public SuperUser() {
    }

    public SuperUser(String nombre, String apellido, String correo, String contrasena) {
        super(nombre, apellido, correo, contrasena);
    }
}
