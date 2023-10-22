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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private TextField txtCodigoIATA;

    @FXML
    private TextField txtPaisDeOrigen;

    @FXML
    private TextField txtCodigoICAO;

    @FXML
    void crearAerolineaAction(ActionEvent event) {


        if (txtNombreAerolinea.getText().equals("") || txtCorreoAerolinea.getText().equals("")
                || txtContrasenaAerolinea.getText().equals("") || txtCodigoIATA.getText().equals("")
                || txtPaisDeOrigen.getText().equals("") || txtCodigoICAO.getText().equals("")) {
            showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");
            return;
        }
        if (!validarFormatoCorreo()) {
            showAlert("Formato de correo incorrecto!", "El correo ingresado no tiene el formato correcto.");
            return;
        }
        if(!validarFormatoCodigo()){
            showAlert("Formato de codigo incorrecto!", "El codigo ingresado no tiene el formato correcto.");
            return;
        }

        String NombreAerolinea = txtNombreAerolinea.getText();
        String Correo = txtCorreoAerolinea.getText();
        String Contrasena = txtContrasenaAerolinea.getText();
        String CodigoIATA = txtCodigoIATA.getText();
        String CodigoICAO = txtCodigoICAO.getText();
        String PaisDeOrigen = txtPaisDeOrigen.getText();
        Aerolinea aerolinea = new Aerolinea();
        AdminAerolinea adminAerolinea = new AdminAerolinea();
        aerolinea.setNombre(NombreAerolinea);
        aerolinea.setCodigoIATA(CodigoIATA);
        aerolinea.setCodigoICAO(CodigoICAO);
        aerolinea.setPaisDeOrigen(PaisDeOrigen);
        adminAerolinea.setNombre("DefaultAdmin");
        adminAerolinea.setApellido(aerolinea.getNombre());
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

    private boolean validarFormatoCodigo() {
        if (txtCodigoIATA.getText().length()==2 && esMayusculas(txtCodigoIATA.getText()) &&
                txtCodigoICAO.getText().length()==3 && esMayusculas(txtCodigoICAO.getText())){
            //asumo que el ICAO son mayusculas tmb
            return true;
        }
        return false;
    }

    private boolean esMayusculas(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isUpperCase(text.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean validarFormatoCorreo() {
        String correo = txtCorreoAerolinea.getText();
        String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    @FXML
    void crearAeropuertoAction(ActionEvent event)  {
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
        AdminAeropuerto adminAeropuerto = new AdminAeropuerto();
        adminAeropuerto.setNombre("DefaultAdmin");
        adminAeropuerto.setApellido(aeropuerto.getNombre());
        adminAeropuerto.setCorreo(correoAeropuerto);
        adminAeropuerto.setContrasena(contrasenaAeropuerto);
        adminAeropuerto.setPasaporte(correoAeropuerto + nombreAeropuerto);
        adminAeropuerto.setAeropuerto(aeropuerto);
        try {
            aeropuertoService.agregarAeropuerto(aeropuerto);
            showAlert("Aeropuerto creado!", "El aeropuerto se ha creado correctamente.");
        } catch (EntidadYaExiste e) {
            showAlert("Error!", "El aeropuerto ya existe.");
            return;
        } catch (InvalidInformation e) {
            showAlert("Error!", "La informacion ingresada no es valida.");
            return;
        }
        try {
            usuarioService.agregarUsuario(adminAeropuerto);
            showAlert("Usuario creado!", "El usuario se ha creado correctamente.");
        } catch (InvalidInformation e) {
            showAlert("Error!", "La informacion ingresada no es valida.");
        } catch (EntidadYaExiste e) {
            showAlert("Error!", "El usuario ya existe.");
        }

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
