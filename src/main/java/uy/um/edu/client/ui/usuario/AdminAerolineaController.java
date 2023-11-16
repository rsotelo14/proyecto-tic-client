package uy.um.edu.client.ui.usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.entities.aerolinea.AdminAerolinea;
import uy.um.edu.client.entities.aerolinea.Aerolinea;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.pasajeros.Pasajero;
import uy.um.edu.client.entities.pasajeros.PasaporteCodigoVuelo;
import uy.um.edu.client.entities.vuelos.Asientos;
import uy.um.edu.client.entities.vuelos.Avion;
import uy.um.edu.client.entities.vuelos.EstadoVuelo;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadNoExiste;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.services.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    @Autowired
    private AerolineaService aerolineaService;
    @FXML
    private TextField txtCodigoVuelo;
    @FXML
    private ChoiceBox<Avion> choiceBoxAvion;


    @FXML
    private ChoiceBox<Aeropuerto> choiceBoxAeropuertoOrigen;
    @FXML
    private ChoiceBox<Aeropuerto> choiceBoxAeropuertoDestino;


    @FXML
    private DatePicker fechaSalidaDate;

    @FXML
    private DatePicker fechaLlegadaDate;

    @FXML
    private ChoiceBox<Integer> horaSalidaHoras;

    @FXML
    private ChoiceBox<Integer> horaSalidaMinutos;
    @FXML
    private ChoiceBox<Integer> horaLlegadaHoras;
    @FXML
    private ChoiceBox<Integer> horaLlegadaMinutos;
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

    private ListView<String> aeropuertosAsociadosListView;


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
    @FXML
    private TextField txtAgregarPasaporte;
    @FXML
    private TextField txtCodigoVueloPasajero;

    @FXML
    private TextField txtCheckInPasaporte;

    @FXML
    private ChoiceBox<Integer> cantidadValijas;
    @FXML
    private TextField txtCheckInCodigoVuelo;
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

        ObservableList<Integer> horas = FXCollections.observableArrayList();
        horas.add(null);
        for (int i = 0; i < 24; i++) {
            horas.add(i);
        }
        horaSalidaHoras.setItems(horas);
        horaLlegadaHoras.setItems(horas);

        ObservableList<Integer> minutos = FXCollections.observableArrayList();
        minutos.add(null);
        for (int i = 0; i < 60; i+= 5) {
            minutos.add(i);
        }
        horaLlegadaMinutos.setItems(minutos);
        horaSalidaMinutos.setItems(minutos);

        cantidadValijas.getItems().addAll(0,1,2,3);
        cantidadValijas.setValue(0);

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

                || fechaSalidaDate.getValue().equals(null) || fechaLlegadaDate.getValue().equals(null)
                || txtCapacidad.getText().equals("")       || choiceBoxAvion.getValue().equals(null)
                || horaSalidaHoras.getValue().equals(null) || horaSalidaMinutos.getValue().equals(null)
                || horaLlegadaHoras.getValue().equals(null) || horaLlegadaMinutos.getValue().equals(null)
        ) {
            showAlert("Datos faltantes!",
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
        Avion avion = this.choiceBoxAvion.getValue();
        LocalDate fechaSalida = fechaSalidaDate.getValue();
        LocalDate fechaLlegada = fechaLlegadaDate.getValue();
        //String codigoAvion = this.txtAvion.getText();
        //Avion avion = avionMgr.obtenerUnoPorCodigo(codigoAvion);

        Integer horaSalidaHoras = this.horaSalidaHoras.getValue();
        Integer horaSalidaMinutos = this.horaSalidaMinutos.getValue();
        Integer horaLlegadaHoras = this.horaLlegadaHoras.getValue();
        Integer horaLlegadaMinutos = this.horaLlegadaMinutos.getValue();
        LocalTime horaSalida = LocalTime.of(horaSalidaHoras, horaSalidaMinutos);
        LocalTime horaLlegada = LocalTime.of(horaLlegadaHoras, horaLlegadaMinutos);
        String capacidad = this.txtCapacidad.getText();
        //crear la instancia vuelo
        Vuelo vuelo = new Vuelo();
        vuelo.setAerolinea(aerolinea);
        vuelo.setCodigoVuelo(codigoVuelo);
        vuelo.setAeropuertoOrigen(aeropuertoOrigen);
        vuelo.setAeropuertoDestino(aeropuertoDestino);
        vuelo.setFechaSalida(fechaSalida);
        vuelo.setFechaLlegada(fechaLlegada);
        vuelo.setHoraSalidaEstimada(horaSalida);
        vuelo.setHoraLlegadaEstimada(horaLlegada);

        vuelo.setCapacidadMaxima(Long.valueOf(capacidad));
        vuelo.setAvion(avion);
        vuelo.setEstado(EstadoVuelo.PENDIENTE);
        int numeroCapacidad=0;
        if (!esNumero(capacidad)){
            showAlert("Error", "La capacidad del vuelo debe ser un número.");
        }
        if (Integer.valueOf(capacidad) > avion.getCapacidad()){
            showAlert("Error", "La capacidad del vuelo no puede ser mayor a la capacidad del avion.");
            return;
        }
        numeroCapacidad = Integer.valueOf(capacidad);

        vuelo.setCapacidadMaxima(Long.valueOf(numeroCapacidad));

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
    void guardarPasajero(ActionEvent event) {
        if (txtNombrePasajero.getText().equals("") || txtApellidoPasajero.getText().equals("") || txtPasaporte.getText().equals("")
                || txtCorreoPasajero.getText().equals("") || txtContraseñaPasajero.getText().equals("")) {
            showAlert("Datos faltantes!",
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
        try {
            pasajeroService.agregarPasajero(pasajero);
            showAlert("Pasajero creado", "El pasajero se ha creado correctamente.");
            limpiarCampos();
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Pasajero ya existe !",
                    "Se encontro un pasajero con el mismo pasaporte");
            return;
        }



    }
    public void mostrarAeropuertosAsociadosAction(ActionEvent event ){
        ObservableList<String> aeropuertosObservable = FXCollections.observableArrayList();
        List<Aeropuerto> aeropuertosAsociados = aerolineaService.obtenerAeropuertosAsociados(aerolinea);
        for (Aeropuerto aeropuerto : aeropuertosAsociados){
            aeropuertosObservable.add(aeropuerto.getNombre()+" "+aeropuerto.getCodigo());

        }
        if (aeropuertosAsociados.size() == 0){
            showAlert("No hay aerolineas asociadas", "No hay aerolineas asociadas a este aeropuerto");
        }

        aeropuertosAsociadosListView.setItems(aeropuertosObservable);

    }
    @FXML
    void asignarPasajero(ActionEvent event) {
        if (txtAgregarPasaporte.getText().equals("") || txtCodigoVueloPasajero.getText().equals("")) {
            showAlert("Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar la asignación del pasajero.");
            return;
        }
        String pasaporte = this.txtAgregarPasaporte.getText();
        String codigoVuelo = this.txtCodigoVueloPasajero.getText();
        PasaporteCodigoVuelo pasaporteCodigoVuelo = new PasaporteCodigoVuelo();
        pasaporteCodigoVuelo.setPasaporte(pasaporte);
        pasaporteCodigoVuelo.setCodigoVuelo(codigoVuelo);
        try {
            pasajeroService.enviarPasaporteCodigoVuelo(pasaporteCodigoVuelo);
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Pasajero ya asociado !",
                    "El pasajero ya está asociado al vuelo");
        } catch (EntidadNoExiste e){
            showAlert(
                    "Pasajero no existe !",
                    "El pasajero no existe");
        }
        showAlert("Pasajero asociado", "El pasajero se ha asociado al vuelo correctamente.");
        limpiarCampos();
    }

    @FXML
    public void checkIn(ActionEvent event) {
        if (txtCheckInPasaporte.getText().equals("") || txtCheckInCodigoVuelo.getText().equals("")) {
            showAlert("Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el check-in.");
            return;
        }
        String pasaporte = this.txtCheckInPasaporte.getText();
        String codigoVuelo = this.txtCheckInCodigoVuelo.getText();
        PasaporteCodigoVuelo pasaporteCodigoVuelo = new PasaporteCodigoVuelo();
        pasaporteCodigoVuelo.setPasaporte(pasaporte);
        pasaporteCodigoVuelo.setCodigoVuelo(codigoVuelo);
        pasaporteCodigoVuelo.setCantidadValijas(cantidadValijas.getValue());
        try {
            pasajeroService.checkIn(pasaporteCodigoVuelo);
            showAlert("Check In", "Se hizo el Check In correctamente");
            limpiarCampos();
        } catch (EntidadYaExiste e) {
            showAlert(
                    "Pasajero ya hizo check in !",
                    "El pasajero ya hizo check in");
        } catch (EntidadNoExiste e){
            showAlert(
                    "Entidad no existe !",
                    e.getMessage());
        }

    }





    private void limpiarCampos(){
        txtCodigoVuelo.setText("");
        choiceBoxAeropuertoOrigen.setValue(null);
        choiceBoxAeropuertoDestino.setValue(null);


        choiceBoxAvion.setValue(null);
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
        choiceBoxAvion.setValue(null);
        txtCapacidad.setText("");
        fechaSalidaDate.setValue(null);
        fechaLlegadaDate.setValue(null);
        horaSalidaHoras.setValue(null);
        horaSalidaMinutos.setValue(null);
        horaLlegadaHoras.setValue(null);
        horaLlegadaMinutos.setValue(null);
        txtAgregarPasaporte.setText("");
        txtCodigoVueloPasajero.setText("");



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
    public static boolean esNumero(String str) {
        try {
            // Intentar convertir el String a un número
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setAdminAerolinea(AdminAerolinea adminAerolinea) {
        this.adminAerolinea = adminAerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
}
