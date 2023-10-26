package uy.um.edu.client.ui.usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.entities.aerolinea.AdminAerolinea;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.pasajeros.Pasajero;
import uy.um.edu.client.entities.vuelos.Avion;
import uy.um.edu.client.entities.vuelos.EstadoVuelo;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.services.*;

import javax.swing.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class AdminAerolineaController {
    @Autowired
    private UsuarioAerolineaService usuarioAerolineaService;
    @Autowired
    private AeropuertoService aeropuertoService;
    @Autowired
    private PasajeroService pasajeroService;
    @Autowired
    private AvionService avionService;
    @Autowired
    private VueloService vueloService;
    @FXML
    private TextField txtCodigoVuelo;
    @FXML
    private ChoiceBox<Avion> choiceBoxAvion;

    @FXML
    private ChoiceBox<Aeropuerto> choiceBoxAeropuertoOrigen;
    @FXML
    private ChoiceBox<Aeropuerto> choiceBoxAeropuertoDestino;
    @FXML
    private TextField txtFechaSalida;
    @FXML
    private TextField txtFechaLlegada;
    @FXML
    private TextField txtHoraSalidaEstimada;
    @FXML
    private TextField txtHoraLlegadaEstimada;
    @FXML
    private TextField txtCapacidad;
    @FXML
    private Label nombreAdminAerolinea;
    @FXML
    private Label nombreAerolinea;
    @FXML
    private TextField txtNombreAvion;
    @FXML
    private TextField txtCapacidadBultoAvion;
    @FXML
    private TextField txtCapacidadAsientosAvion;
    @FXML
    private TextField txtTipoAvion;
    @FXML
    private TextField txtCodigoAvion;

    @FXML
    private TextField txtCorreoPasajero;
    @FXML
    private TextField txtContraseñaPasajero;
    @FXML
    private TextField txtNombrePasajero;
    @FXML
    private TextField txtApellidoPasajero;
    @FXML
    private TextField getTxtNombrePasajero;
    @FXML
    private TextField txtPasaporte;
    private AdminAerolinea adminAerolinea;
    private Aerolinea aerolinea;
    private List<Aeropuerto> aeropuertosAsociados;
    private List<Avion> avionesAsociados;
    public void initialize(){
        this.nombreAerolinea.setText(aerolinea.getNombre());
        this.nombreAdminAerolinea.setText(adminAerolinea.getNombre());

        aeropuertosAsociados = (List<Aeropuerto>) aeropuertoService.obtenerAeropuertosPorAerolinea(aerolinea);
        avionesAsociados= (List<Avion>) avionService.obtenerAvionesAerolinea(aerolinea);
        choiceBoxAeropuertoOrigen.getItems().addAll(aeropuertosAsociados);
        choiceBoxAeropuertoOrigen.getItems().add(null);
        choiceBoxAeropuertoDestino.getItems().addAll(aeropuertosAsociados);
        choiceBoxAeropuertoDestino.getItems().add(null);
        choiceBoxAvion.getItems().addAll(avionesAsociados);
        choiceBoxAvion.getItems().add(null);
    }
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void guardarVuelo(ActionEvent event){
        if (txtCodigoVuelo.getText().equals("") || choiceBoxAeropuertoOrigen.getValue().equals(null) || choiceBoxAeropuertoDestino.getValue().equals(null)
                || txtFechaSalida.getText().equals("") || txtFechaLlegada.getText().equals("")
                || txtHoraSalidaEstimada.getText().equals("") || choiceBoxAvion.getValue().equals(null)
                || txtHoraLlegadaEstimada.getText().equals("") || txtCapacidad.getText().equals("")){
            showAlert( "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar la creación del vuelo.");
            return;
        }
        //Obtener datos ingresados por el usuario
        String codigoVuelo = this.txtCodigoVuelo.getText();
        Aeropuerto aeropuertoOrigen = this.choiceBoxAeropuertoOrigen.getValue();
        Aeropuerto aeropuertoDestino = this.choiceBoxAeropuertoDestino.getValue();

        if (aeropuertoOrigen == null || aeropuertoDestino == null){
            showAlert("Error", "Debe seleccionar un aeropuerto de origen y uno de destino.");
            return;
        }
         if(aeropuertoDestino == aeropuertoOrigen){
            showAlert("Error", "El aeropuerto de destino no puede ser el mismo que el de origen.");
            return;
        }
        String fechaSalida = this.txtFechaSalida.getText();
        String fechaLlegada = this.txtFechaLlegada.getText();
        Avion avion = this.choiceBoxAvion.getValue();
        String horaSalidaEstimada = this.txtHoraSalidaEstimada.getText();
        String horaLlegadaEstimada = this.txtHoraLlegadaEstimada.getText();
        String capacidad = this.txtCapacidad.getText();
        //crear la instancia vuelo
        Vuelo vuelo = new Vuelo();
        vuelo.setAerolinea(aerolinea);
        vuelo.setCodigoVuelo(codigoVuelo);
        vuelo.setAeropuertoOrigen(aeropuertoOrigen);
        vuelo.setAeropuertoDestino(aeropuertoDestino);
        vuelo.setFechaSalida(fechaSalida);
        vuelo.setFechaLlegada(fechaLlegada);
        vuelo.setHoraSalidaEstimada(horaSalidaEstimada);
        vuelo.setHoraLlegadaEstimada(horaLlegadaEstimada);

        vuelo.setCapacidadMaxima(Long.valueOf(capacidad));
        vuelo.setAvion(avion);
        vuelo.setEstado(EstadoVuelo.PENDIENTE);
        //guardar en la base de datos
        try {
            vueloService.agregarVuelo(vuelo);
            showAlert("Vuelo creado", "El vuelo se ha creado correctamente.");
        } catch (InvalidInformation e) {

            showAlert("Error", "La información ingresada no es válida.");
            return;
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Vuelo ya existe !",
                    "Se encontro un vuelo con el mismo código");
            return;
        }
        limpiarCampos();


    }
    @FXML
    void guardarAvion(ActionEvent event){
        if (txtCodigoAvion.getText().equals("") || txtNombreAvion.getText().equals("") || txtCapacidadBultoAvion.getText().equals("")
                || txtCapacidadAsientosAvion.getText().equals("") || txtTipoAvion.getText().equals("")){
            showAlert( "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar la creación del avion.");
            return;
        }
        //Obtener datos ingresados por el usuario
        String codigoAvion = this.txtCodigoAvion.getText();
        String nombreAvion = this.txtNombreAvion.getText();
        String capacidadBultoAvion = this.txtCapacidadBultoAvion.getText();
        String capacidadAsientosAvion = this.txtCapacidadAsientosAvion.getText();
        String tipoAvion = this.txtTipoAvion.getText();
        //crear la instancia avion
        Avion avion = new Avion();
        avion.setCodigo(codigoAvion);
        avion.setAerolinea(aerolinea);
        avion.setNombre(nombreAvion);
        avion.setCapacidad(Long.valueOf(capacidadBultoAvion));
        avion.setCapacidad(Long.valueOf(capacidadAsientosAvion));
        avion.setTipoAvion(tipoAvion);
        //guardar en la base de datos
        try{
            avionService.agregarAvion(avion);
            showAlert("Avion creado", "El avion se ha creado correctamente.");
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Avion ya existe !",
                    "Se encontro un avion con el mismo código");
            return;
        }
        limpiarCampos();

    }
    @FXML
    void guardarPasajero(ActionEvent event){
        if (txtNombrePasajero.getText().equals("") || txtApellidoPasajero.getText().equals("") || txtPasaporte.getText().equals("")
                || txtCorreoPasajero.getText().equals("") || txtContraseñaPasajero.getText().equals("")){
            showAlert( "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar la creación del pasajero.");
            return;
        }
        if (!validarFormatoCorreo(txtCorreoPasajero.getText())) {
            showAlert("Formato de correo incorrecto!", "El correo ingresado no tiene el formato correcto.");
            return;
        }
        //Obtener datos ingresados por el usuario
        String nombrePasajero = this.txtNombrePasajero.getText();
        String apellidoPasajero = this.txtApellidoPasajero.getText();
        String pasaporte = this.txtPasaporte.getText();
        String correoPasajero = this.txtCorreoPasajero.getText();
        String contraseñaPasajero = this.txtContraseñaPasajero.getText();
        //crear la instancia pasajero
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(nombrePasajero);
        pasajero.setApellido(apellidoPasajero);
        pasajero.setPasaporte(pasaporte);
        pasajero.setCorreo(correoPasajero);
        pasajero.setContrasena(contraseñaPasajero);
        //guardar en la base de datos
        try{
            pasajeroService.agregarPasajero(pasajero);
            showAlert("Pasajero creado", "El pasajero se ha creado correctamente.");
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Pasajero ya existe !",
                    "Se encontro un pasajero con el mismo pasaporte");
            return;
        }







        limpiarCampos();
    }
    private void limpiarCampos(){
        txtCodigoVuelo.setText("");
        choiceBoxAeropuertoOrigen.setValue(null);
        choiceBoxAeropuertoDestino.setValue(null);
        txtFechaSalida.setText("");
        txtFechaLlegada.setText("");
        choiceBoxAvion.setValue(null);
        txtHoraSalidaEstimada.setText("");
        txtHoraLlegadaEstimada.setText("");
        txtCapacidad.setText("");
        txtCodigoAvion.setText("");
        txtNombreAvion.setText("");
        txtCapacidadBultoAvion.setText("");
        txtCapacidadAsientosAvion.setText("");
        txtTipoAvion.setText("");
        txtApellidoPasajero.setText("");
        txtNombrePasajero.setText("");
        txtPasaporte.setText("");
        txtCorreoPasajero.setText("");
        txtContraseñaPasajero.setText("");



    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
    private boolean validarFormatoCorreo(String correo) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public void setAdminAerolinea(AdminAerolinea adminAerolinea) {
        this.adminAerolinea = adminAerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
}
