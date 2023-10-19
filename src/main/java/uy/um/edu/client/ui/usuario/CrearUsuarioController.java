package uy.um.edu.client.ui.usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.entities.aeropuerto.AdminAeropuerto;
import uy.um.edu.client.entities.aeropuerto.MaleteroAeropuerto;
import uy.um.edu.client.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.exceptions.UsuarioYaExiste;
import uy.um.edu.client.services.UsuarioService;


@Controller
public class CrearUsuarioController {

   @Autowired
   private UsuarioService usuarioService;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtPasaporte;

    @FXML
    private RadioButton adminToggle;
    @FXML
    private RadioButton maleteroToggle;

    private ToggleGroup toggleGroup;

    public void initialize(){
        toggleGroup = new ToggleGroup();
        adminToggle.setToggleGroup(toggleGroup);
        maleteroToggle.setToggleGroup(toggleGroup);
        adminToggle.setSelected(true);
        maleteroToggle.setSelected(false);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    void guardarUsuario(ActionEvent event) {

        if (txtNombre.getText().equals("") || txtApellido.getText().equals("")
            || txtCorreo.getText().equals("") || txtPasaporte.getText().equals("")
            || txtContrasena.getText().equals("")
        ) {
            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");
                    return;
        }

  // Obtener datos ingresados por el usuario
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String pasaporte = txtPasaporte.getText();
        String contrasena = txtContrasena.getText();


            // Crear una nueva instancia de Usuario
        UsuarioAeropuerto usuario;
        if (adminToggle.isSelected()){
             usuario = new AdminAeropuerto();
        }else {
             usuario = new MaleteroAeropuerto();
        }
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setPasaporte(pasaporte);
        //TODO: Encriptar o hashear contraseña
        usuario.setContrasena(contrasena);

        // Guardar el usuario en la base de datos
        try {
            usuarioService.agregarUsuario(usuario);
            // Puedes mostrar una confirmación al usuario si lo deseas
            showAlert("Usuario guardado", "El usuario se ha guardado correctamente.");
        } catch (InvalidInformation e) {
            showAlert(
                    "Informacion invalida !",
                    "Se encontro un error en los datos ingresados.");
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Usuario ya existe !",
                    "Se encontro un usuario con el mismo pasaporte");
        }



        // Limpiar los campos de entrada
        limpiarCampos();
    }


    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtPasaporte.clear();
        txtContrasena.clear();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
