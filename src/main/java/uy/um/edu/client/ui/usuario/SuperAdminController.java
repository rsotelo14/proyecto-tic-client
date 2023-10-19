package uy.um.edu.client.ui.usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.entities.aerolinea.AdminAerolinea;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.AdminAeropuerto;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.exceptions.UsuarioYaExiste;
import uy.um.edu.client.services.AerolineaService;
import uy.um.edu.client.services.AeropuertoService;
import uy.um.edu.client.services.UsuarioService;


@Controller
public class SuperAdminController {

    @Autowired
    private AeropuertoService aeropuertoService;
  
    @Autowired
    private AerolineaService aerolineaService;

    @Autowired
    private UsuarioService usuarioService;
    @FXML
    private TextField txtNombreAerolinea;
    @FXML
    private TextField txtCorreoAerolinea;
    @FXML
    private TextField txtContrasenaAerolinea;
    @FXML
    private TextField txtNombreAeropuerto;
    @FXML
    private TextField txtCuidad;

    @FXML
    private TextField txtCodigoAeropuerto;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreoAeropuerto;
    @FXML
    private TextField txtContrasenaAeropuerto;


    @FXML
    void crearAerolineaAction(ActionEvent event) {


        if (txtNombreAerolinea.getText().equals("") || txtCorreoAerolinea.getText().equals("") || txtContrasenaAerolinea.getText().equals("")) {
            showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");
            return;
        }

        String NombreAerolinea = txtNombreAerolinea.getText();
        String Correo = txtCorreoAerolinea.getText();
        String Contrasena = txtContrasenaAerolinea.getText();
        Aerolinea aerolinea = new Aerolinea();
        AdminAerolinea adminAerolinea = new AdminAerolinea();
        aerolinea.setNombre(NombreAerolinea);
        adminAerolinea.setNombre("DefaultAdmin");
        adminAerolinea.setApellido("DefaultAdmin");
        adminAerolinea.setCorreo(Correo);
        adminAerolinea.setContrasena(Contrasena);
        adminAerolinea.setPasaporte("DefaultAdmin" + NombreAerolinea);
        adminAerolinea.setAerolinea(aerolinea);
        //falta anadir la aerolinea y el admin a la base de datos
        try{
            aerolineaService.agregarAerolinea(aerolinea);

            showAlert("Aerolinea creada!", "La aerolinea se ha creado correctamente.");
        }catch (EntidadYaExiste e) {
            showAlert("Error!", "La aerolínea ya existe.");
            return;
        }
        try{
            usuarioService.agregarUsuario(adminAerolinea);
            showAlert("Usuario creado!", "El usuario se ha creado correctamente.");
        } catch (EntidadYaExiste e) {
            showAlert("Error!", "El usuario ya existe.");
        } catch (InvalidInformation e) {
            throw new RuntimeException(e);
        }
        limpiarCampos();

    }

    @FXML
    void crearAeropuertoAction(ActionEvent event) throws InvalidInformation, EntidadYaExiste, UsuarioYaExiste {
        if (txtNombreAeropuerto.getText().equals("") || txtCuidad.getText().equals("")
            || txtPais.getText().equals("") || txtDireccion.getText().equals("")
            || txtTelefono.getText().equals("") || txtCorreoAeropuerto.getText().equals("")
             || txtContrasenaAeropuerto.getText().equals("") || txtCodigoAeropuerto.getText().equals("") ) {
            showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");
            return;
        }
        String codigoAeropuerto = txtCodigoAeropuerto.getText();
        
        if (aeropuertoService.obtenerUnoPorCodigo(codigoAeropuerto)!=null){
            showAlert("Codigo ya existe!", "El codigo ingresado ya existe.");
            return;
        }
        String correoAeropuerto = txtCorreoAeropuerto.getText();
        if (usuarioService.obtenerUnoPorCorreo(correoAeropuerto)!=null){
            showAlert("Correo ya existe!", "El correo ingresado ya existe.");
            return;
        }

        String nombreAeropuerto = txtNombreAeropuerto.getText();
        String cuidad = txtCuidad.getText();
        String pais = txtPais.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String contrasenaAeropuerto = txtContrasenaAeropuerto.getText();

        Aeropuerto aeropuerto = new Aeropuerto(nombreAeropuerto, cuidad, pais, codigoAeropuerto, direccion, telefono);
        AdminAeropuerto adminAerolinea = new AdminAeropuerto();
        adminAerolinea.setNombre("DefaultAdmin");
        adminAerolinea.setApellido(aeropuerto.getNombre());
        adminAerolinea.setCorreo(correoAeropuerto);
        adminAerolinea.setContrasena(contrasenaAeropuerto);
        adminAerolinea.setPasaporte(correoAeropuerto + nombreAeropuerto);
        adminAerolinea.setAeropuerto(aeropuerto);
        aeropuertoService.agregarAeropuerto(aeropuerto);
        usuarioService.agregarUsuario(adminAerolinea);
        showAlert("Aeropuerto creado!", "El aeropuerto se ha creado correctamente.");

    }


    private void limpiarCampos() {
        txtNombreAerolinea.clear();
        txtCorreoAerolinea.clear();
        txtContrasenaAerolinea.clear();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


}
