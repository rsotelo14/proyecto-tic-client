package uy.um.edu.client.entities.aerolinea;



public class AdminAerolinea extends UsuarioAerolinea{
    private String codigoAdmin;

    public AdminAerolinea(String nombre, String apellido, String email, String password, Aerolinea aerolinea, String codigoAdmin) {
        super(nombre, apellido, email, password, aerolinea);
        this.codigoAdmin = codigoAdmin;
    }

    public AdminAerolinea() {
    }

    public String getCodigoAdmin() {
        return codigoAdmin;
    }

    public void setCodigoAdmin(String codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }
}
