package uy.um.edu.client.ui.usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import uy.um.edu.client.entities.aeropuerto.Aeropuerto;
import uy.um.edu.client.entities.aeropuerto.PistaAeropuerto;
import uy.um.edu.client.entities.aeropuerto.PuertaAeropuerto;
import uy.um.edu.client.entities.vuelos.ReservaPista;
import uy.um.edu.client.entities.vuelos.ReservaPuerta;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.services.ReservasDTO;
import uy.um.edu.client.services.ReservasService;

import java.time.LocalTime;
import java.util.List;


@Controller
public class ValidarVueloController {


    @Autowired
    ReservasService reservasService;
    private Aeropuerto aeropuerto;
    private Vuelo vuelo;
    private List<PuertaAeropuerto> puertas;
    private List<PistaAeropuerto> pistas;


    @FXML
    private ChoiceBox<Integer> horaInicioPuertaHorasChoiceBox;
    @FXML
    private ChoiceBox<Integer> horaInicioPuertaMinutosChoiceBox;

    @FXML
    private ChoiceBox<Integer> horaInicioPistaHorasChoiceBox;
    @FXML
    private ChoiceBox<Integer> horaInicioPistaMinutosChoiceBox;

    @FXML
    private ChoiceBox<Integer> cantidadHorasPuertaChoiceBox;



    @FXML
    private ChoiceBox<PuertaAeropuerto> puertaChoiceBox;

    @FXML
    private ChoiceBox<PistaAeropuerto> pistaChoiceBox;

    @FXML
    private Label codigoAerolineaLabel;

    @FXML
    private Label codigoVueloLabel;
    @FXML
    private Label codigoAeropuertoOrigenLabel;
    @FXML
    private Label codigoAeropuertoDestinoLabel;
    @FXML
    private Label fechaSalidaLabel;
    @FXML
    private Label fechaLlegadaLabel;
    @FXML
    private Label horaSalidaLabel;
    @FXML
    private Label horaLlegadaLabel;

    @FXML
    private Button validarButton;
    @FXML
    private Button cancelarButton;




    public void setVuelo(Vuelo item) {
        this.vuelo = item;
    }



    public void initialize() {
        System.out.println("ValidarVueloController.initialize");
        codigoAerolineaLabel.setText(vuelo.getAerolinea().getCodigoIATA());
        codigoVueloLabel.setText(vuelo.getCodigoVuelo());
        codigoAeropuertoOrigenLabel.setText(vuelo.getAeropuertoOrigen().getCodigo());
        codigoAeropuertoDestinoLabel.setText(vuelo.getAeropuertoDestino().getCodigo());
        fechaSalidaLabel.setText(vuelo.getFechaSalida().toString());
        fechaLlegadaLabel.setText(vuelo.getFechaLlegada().toString());
        horaSalidaLabel.setText(vuelo.getHoraSalidaEstimada().toString());
        horaLlegadaLabel.setText(vuelo.getHoraLlegadaEstimada().toString());

        puertaChoiceBox.getItems().addAll(puertas);
        puertaChoiceBox.getItems().add(null);
        pistaChoiceBox.getItems().addAll(pistas);
        pistaChoiceBox.getItems().add(null);

        ObservableList<Integer> horas = FXCollections.observableArrayList();
        horas.add(null);
        for (int i = 0; i < 24; i++) {
            horas.add(i);
        }
        horaInicioPuertaHorasChoiceBox.setItems(horas);
        horaInicioPistaHorasChoiceBox.setItems(horas);

        ObservableList<Integer> minutos = FXCollections.observableArrayList();
        minutos.add(null);
        for (int i = 0; i < 60; i+= 5) {
            minutos.add(i);
        }
        horaInicioPistaMinutosChoiceBox.setItems(minutos);
        horaInicioPuertaMinutosChoiceBox.setItems(minutos);

        cantidadHorasPuertaChoiceBox.getItems().add(1);
        cantidadHorasPuertaChoiceBox.getItems().add(2);
        cantidadHorasPuertaChoiceBox.getItems().add(3);


    }


    @FXML
     void validarVueloAction(ActionEvent event) {
        if (puertaChoiceBox.getValue() == null || pistaChoiceBox.getValue() == null
            || horaInicioPuertaHorasChoiceBox.getValue() == null || horaInicioPuertaMinutosChoiceBox.getValue() == null
            || horaInicioPistaHorasChoiceBox.getValue() == null || horaInicioPistaMinutosChoiceBox.getValue() == null ||
                cantidadHorasPuertaChoiceBox.getValue() == null){
            showAlert("Datos faltantes", "Por favor, complete todos los datos");
            return;
        }
        ReservaPista reservaPista = new ReservaPista();
        ReservaPuerta reservaPuerta = new ReservaPuerta();
        reservaPuerta.setVuelo(vuelo);
        reservaPista.setVuelo(vuelo);
        reservaPista.setPista(pistaChoiceBox.getValue());
        reservaPuerta.setPuerta(puertaChoiceBox.getValue());
        if (aeropuerto.equals(vuelo.getAeropuertoOrigen())) {
            reservaPista.setFecha(vuelo.getFechaSalida());
            reservaPuerta.setFecha(vuelo.getFechaSalida());
        } else {
            reservaPista.setFecha(vuelo.getFechaLlegada());
            reservaPuerta.setFecha(vuelo.getFechaLlegada());
        }
        LocalTime horaInicioPista = LocalTime.of(horaInicioPistaHorasChoiceBox.getValue(), horaInicioPistaMinutosChoiceBox.getValue());
        reservaPista.setHoraInicio(horaInicioPista);

        LocalTime horaInicioPuerta = LocalTime.of(horaInicioPuertaHorasChoiceBox.getValue(), horaInicioPuertaMinutosChoiceBox.getValue());
        reservaPuerta.setHoraInicio(horaInicioPuerta);
        reservaPuerta.setHoraFin(horaInicioPuerta.plusHours(cantidadHorasPuertaChoiceBox.getValue()));

        try {
            reservasService.reservarPistaYPuerta(reservaPista, reservaPuerta);
            showAlert("Exito", "Se ha validado el vuelo correctamente");
            cancelarAction(event);
        } catch (InvalidInformation e) {
            showAlert("Error", e.getMessage());
        }



    }

    @FXML
    public void cancelarAction(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setPistas(List<PistaAeropuerto> pistas) {
        this.pistas = pistas;
    }

    public void setPuertas(List<PuertaAeropuerto> puertas) {
        this.puertas = puertas;
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}

