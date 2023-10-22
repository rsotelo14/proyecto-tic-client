package uy.um.edu.client.ui.usuario;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.ClientApplication;
import uy.um.edu.client.entities.SuperUser;
import uy.um.edu.client.entities.Usuario;
import uy.um.edu.client.entities.aerolinea.AdminAerolinea;
import uy.um.edu.client.entities.aeropuerto.AdminAeropuerto;
import uy.um.edu.client.entities.aeropuerto.MaleteroAeropuerto;
import uy.um.edu.client.services.AeropuertoService;
import uy.um.edu.client.services.UsuarioService;


import java.io.IOException;


@Controller("LogInController")
public class LogInController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AeropuertoService aeropuertoService;


    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasena;

    @Autowired
    private UsuarioService usuarioManager;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void inicioDeSesion(ActionEvent event) {

//        if (txtCorreo.getText().equals("super") || txtContrasena.getText().equals("user")) {
//
//            SuperUser superUser = new SuperUser();
//            superUser.setNombre("Super");
//            superUser.setApellido("User");
//            superUser.setCorreo("asd");
//            superUser.setContrasena("asd");
//            superUser.setPasaporte("123");
//            try {
//                usuarioManager.agregarUsuario(superUser);
//            } catch (InvalidInformation e) {
//                throw new RuntimeException(e);
//            } catch (UsuarioYaExiste e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }

//        if (txtCorreo.getText().equals("crear") || txtContrasena.getText().equals("aeropuerto")) {
//
//            Aeropuerto aeropuerto = new Aeropuerto();
//            aeropuerto.setNombre("aeropuerto1");
//            aeropuerto.setCiudad("Montevideo");
//            aeropuerto.setPais("Uruguay");
//            aeropuerto.setDireccion("Direccion");
//            aeropuerto.setTelefono("12345678");
//            aeropuerto.setCodigo("MVD");
//            try {
//                aeropuertoService.agregarAeropuerto(aeropuerto);
//            } catch (InvalidInformation e) {
//                throw new RuntimeException(e);
//            } catch (EntidadYaExiste e) {
//                throw new RuntimeException(e);
//            }
//            AdminAeropuerto adminAeropuerto = new AdminAeropuerto();
//            adminAeropuerto.setNombre("Admin");
//            adminAeropuerto.setApellido("Aeropuerto1");
//            adminAeropuerto.setCorreo("asda");
//            adminAeropuerto.setContrasena("asda");
//            adminAeropuerto.setPasaporte("1234");
//            adminAeropuerto.setAeropuerto(aeropuerto);
//            try {
//                usuarioManager.agregarUsuario(adminAeropuerto);
//            } catch (InvalidInformation e) {
//                throw new RuntimeException(e);
//            } catch (UsuarioYaExiste e) {
//                throw new RuntimeException(e);
//            }
//            aeropuerto.getUsuarios().add(adminAeropuerto);
//
//
//        }

        if (txtCorreo.getText().equals("") || txtContrasena.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");
            return;

        }
        boolean found= false;
        String correo = txtCorreo.getText();
        String contraseña = txtContrasena.getText();
        Usuario usuarioEncontrado = null;
        try {
            usuarioEncontrado = usuarioService.obtenerUnoPorCorreo(correo);
            if (usuarioEncontrado != null) {
                if (usuarioEncontrado.getContrasena().equals(contraseña)) {
                    found = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!found) {
            showAlert("Inicio de sesión fallido", "El correo o la contraseña son incorrectos");
            return;
        }
        showAlert("Inicio de sesión exitoso", "Bienvenido " + usuarioEncontrado.getNombre() + " " + usuarioEncontrado.getApellido());
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        if (usuarioEncontrado instanceof AdminAeropuerto) {
            AdministradorAeropuertoController controller = ClientApplication.getContext().getBean(AdministradorAeropuertoController.class);
            controller.setAeropuerto(((AdminAeropuerto) usuarioEncontrado).getAeropuerto());
            controller.setAdmin((AdminAeropuerto) usuarioEncontrado);

            try {
                fxmlLoader.setLocation(LogInController.class.getResource("AdminAeropuerto.fxml"));
                fxmlLoader.setController(controller);
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (usuarioEncontrado instanceof MaleteroAeropuerto) {
            try {
                root = fxmlLoader.load(LogInController.class.getResourceAsStream("MaleteroAeropuerto.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (usuarioEncontrado instanceof SuperUser) {
            SuperAdminController controller = ClientApplication.getContext().getBean(SuperAdminController.class);
            try {
                fxmlLoader.setLocation(LogInController.class.getResource("SuperUser.fxml"));
                fxmlLoader.setController(controller);
                root = fxmlLoader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (usuarioEncontrado instanceof AdminAerolinea){
            AdminAerolineaController controller = ClientApplication.getContext().getBean(AdminAerolineaController.class);
            controller.setAdminAerolinea((AdminAerolinea) usuarioEncontrado);
            controller.setAerolinea(((AdminAerolinea) usuarioEncontrado).getAerolinea());
            try {
                fxmlLoader.setLocation(LogInController.class.getResource("AdminAerolinea.fxml"));
                fxmlLoader.setController(controller);
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (root == null)throw new RuntimeException("No se pudo cargar la pantalla");

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        close(event);

    }

    private void limpiarCampos() {

        txtCorreo.clear();
        txtContrasena.clear();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void pantallaMaleteroAction(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(LogInController.class.getResourceAsStream("MaleteroAeropuerto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void pantallaAdministradorAction(ActionEvent event) throws Exception {


    }


}
