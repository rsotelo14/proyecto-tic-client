package uy.um.edu.client.ui.usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.ClientApplication;
import uy.um.edu.client.entities.Usuario;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.AdminAeropuerto;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.MaleteroAeropuerto;
import uy.um.edu.client.entities.aeropuerto.UsuarioAeropuerto;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.exceptions.UsuarioYaExiste;
import uy.um.edu.client.services.AerolineaService;
import uy.um.edu.client.services.AeropuertoService;
import uy.um.edu.client.services.UsuarioService;
import uy.um.edu.client.services.VueloService;

import java.util.List;


@Controller
public class AdministradorAeropuertoController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private VueloService vueloService;

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


    @FXML
    private TableView<Usuario> administradoresTableView;
    @FXML
    private TableColumn<Usuario,String> nombreAdminColumn;
    @FXML
    private TableColumn<Usuario,String> apellidoAdminColumn;
    @FXML
    private TableColumn<Usuario,String> correoAdminColumn;
    @FXML
    private TableColumn<Usuario,String> pasaporteAdminColumn;
    @FXML
    private TableView<Usuario> maleterosTableView;
    @FXML
    private TableColumn<Usuario,String> nombreMaleteroColumn;
    @FXML
    private TableColumn<Usuario,String> apellidoMaleteroColumn;
    @FXML
    private TableColumn<Usuario,String> correoMaleteroColumn;
    @FXML
    private TableColumn<Usuario,String> pasaporteMaleteroColumn;
    @FXML
    private ListView<Vuelo> vuelosListView;
    @FXML
    private Label administradoresLabel;

    @FXML
    private Label maleterosLabel;

    @FXML
    private Label nombreAeropuertoLabel;
    @FXML
    private Label bienvenidoLabel;

    @FXML
    private Label nombreAdminLabel;

    @FXML
    private Label aeropuertoLabel;

    @FXML
    private ListView<Aerolinea> aerolineasDisponiblesListView;

    @FXML
    private ListView<String> aerolineasAsociadasListView;
    private AdminAeropuerto adminAeropuerto;
    private Aeropuerto aeropuerto;



    public void setAdmin(AdminAeropuerto adminAeropuerto) {
        this.adminAeropuerto = adminAeropuerto;
    }
    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    public void initialize(){
        // Toggle group
        toggleGroup = new ToggleGroup();
        adminToggle.setToggleGroup(toggleGroup);
        maleteroToggle.setToggleGroup(toggleGroup);
        adminToggle.setSelected(true);
        maleteroToggle.setSelected(false);

        // Mostrar Nombre Aeropuerto
        this.nombreAeropuertoLabel.setText(aeropuerto.getNombre());
        this.nombreAdminLabel.setText(adminAeropuerto.getNombre() + " " + adminAeropuerto.getApellido());
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
        Usuario usuario;
        if (adminToggle.isSelected()){
            usuario = new AdminAeropuerto(nombre, apellido, correo, contrasena, aeropuerto);


        }else {
            usuario = new MaleteroAeropuerto(nombre, apellido, correo, contrasena, aeropuerto);
        }
//        usuario.setNombre(nombre);
//        usuario.setApellido(apellido);
//        usuario.setCorreo(correo);
        usuario.setPasaporte(pasaporte);
        //TODO: Encriptar o hashear contraseña
//        usuario.setContrasena(contrasena);

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



        aeropuerto.getUsuarios().add((UsuarioAeropuerto) usuario);
        // Limpiar los campos de entrada
        limpiarCampos();
    }

    @FXML
    void mostrarUsuariosAction(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        ObservableList<Usuario> administradores = FXCollections.observableArrayList();
        ObservableList<Usuario> maleteros = FXCollections.observableArrayList();
        Iterable<UsuarioAeropuerto> usuarioAeropuertos = aeropuertoService.obtenerUsuarios(aeropuerto);
        for (UsuarioAeropuerto usuario : usuarioAeropuertos){
            boolean isAdmin = usuario instanceof AdminAeropuerto;
            boolean isMaletero = usuario instanceof MaleteroAeropuerto;
//            usuarioNombres.add(usuario.getNombre() + ", " + isAdmin + ", " + isMaletero);
            if (isAdmin){

                administradores.add(usuario);
            }else if (isMaletero){

                maleteros.add(usuario);
            }
        }
        nombreAdminColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Nombre"));
        apellidoAdminColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Apellido"));
        correoAdminColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Correo"));
        pasaporteAdminColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Pasaporte"));
        nombreMaleteroColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Nombre"));
        apellidoMaleteroColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Apellido"));
        correoMaleteroColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Correo"));
        pasaporteMaleteroColumn.setCellValueFactory(new PropertyValueFactory<Usuario,String>("Pasaporte"));
        administradoresTableView.setItems(administradores);
        maleterosTableView.setItems(maleteros);
        administradoresLabel.setVisible(true);
        maleterosLabel.setVisible(true);
        administradoresTableView.setVisible(true);
        maleterosTableView.setVisible(true);

    }

    @FXML
    public void mostrarAerolineasDisponiblesAction(ActionEvent event){
        ObservableList<Aerolinea> aerolineasObservable = FXCollections.observableArrayList();
        List<Aerolinea> aerolineasDisponibles = aeropuertoService.obtenerAerolineasDisponibles(aeropuerto);
        aerolineasObservable.addAll(aerolineasDisponibles);
        if (aerolineasDisponibles.size() == 0){
            showAlert("No hay aerolineas disponibles", "No hay aerolineas disponibles para registrar");
        }
        aerolineasDisponiblesListView.setCellFactory(param -> new ListCell<Aerolinea>() {
            @Override
            protected void updateItem(Aerolinea item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getNombre() == null) {
                    setText(null);
                } else {
                    HBox hbox = new HBox(10); // Espaciado entre elementos
                    Label label = new Label(item.getNombre());
                    Button asociarBtn = new Button("Asociar");

                    asociarBtn.setOnAction(e -> {
                        // Lógica para asociar la aerolinea
                        try {
                            aeropuertoService.asociarAerolinea(aeropuerto, item);
                            showAlert("Aerolinea asociada", "La aerolinea se ha asociado correctamente");
                        } catch (InvalidInformation ex) {
                            showAlert("Error", "Ha ocurrido un error. El aeropuerto no es ni el de origen ni el de destino");
                        }
                        System.out.println("Asociar aerolinea" + item.getNombre());
                    });

                    HBox.setHgrow(label, Priority.ALWAYS);
                    hbox.getChildren().addAll(label, asociarBtn);
                    setGraphic(hbox);
                }
            }
        });
        aerolineasDisponiblesListView.setItems(aerolineasObservable);


    }
    @FXML
    public void mostrarAerolineasAsociadasAction(ActionEvent event){
        ObservableList<String> aerolineasObservable = FXCollections.observableArrayList();
        List<Aerolinea> aerolineasAsociadas = aeropuertoService.obtenerAerolineasAsociadas(aeropuerto);

        for (Aerolinea aerolinea : aerolineasAsociadas)
            aerolineasObservable.add(aerolinea.getNombre());
        if (aerolineasAsociadas.size() == 0){
            showAlert("No hay aerolineas asociadas", "No hay aerolineas asociadas a este aeropuerto");
        }

        aerolineasAsociadasListView.setItems(aerolineasObservable);
    }

    @FXML
    public void mostrarVuelosPendientesAction(ActionEvent event){
        ObservableList<Vuelo> vuelos = FXCollections.observableArrayList();
        Iterable<Vuelo> vuelosIterable = aeropuertoService.obtenerVuelosPendientes(aeropuerto);
        //Iterable<Vuelo> vuelosIterable = vueloService.obtenerTodo();
        for (Vuelo vuelo: vuelosIterable){
            vuelos.add(vuelo);
            System.out.println(vuelo.getCodigoVuelo());
        }
        vuelosListView.setItems(vuelos);

        vuelosListView.setCellFactory(param -> new ListCell<Vuelo>() {
            @Override
            protected void updateItem(Vuelo item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getCodigoVuelo() == null) {
                    setText(null);
                } else {
                    HBox hbox = new HBox(10); // Espaciado entre elementos
                    Label label = new Label(item.getCodigoVuelo() + " - " + item.getAeropuertoOrigen().getNombre() + " a " + item.getAeropuertoDestino().getNombre());
                    Button validarBtn = new Button("Validar");
                    Button rechazarBtn = new Button("Rechazar");

                    validarBtn.setOnAction(e -> {
                        // Lógica para validar el vuelo
                        try {
                            vueloService.validarVuelo(item, aeropuerto);
                            showAlert("Vuelo validado", "El vuelo se ha validado correctamente");
                        } catch (InvalidInformation ex) {
                            showAlert("Error", "Ha ocurrido un error. El aeropuerto no es ni el de origen ni el de destino");
                        }
                        System.out.println("Validar vuelo" + item.getCodigoVuelo());
                    });

                    rechazarBtn.setOnAction(e -> {
                        // Lógica para rechazar el vuelo

                        try {
                            vueloService.rechazarVuelo(item, aeropuerto);
                            showAlert("Vuelo rechazado", "El vuelo se ha rechazado correctamente");
                        } catch (InvalidInformation ex) {
                            showAlert("Error", "Ha ocurrido un error. El aeropuerto no es ni el de origen ni el de destino");

                        }
                    });

                    HBox.setHgrow(label, Priority.ALWAYS);
                    hbox.getChildren().addAll(label, validarBtn, rechazarBtn);
                    setGraphic(hbox);
                }
            }
        });

        vuelosListView.setVisible(true);

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


