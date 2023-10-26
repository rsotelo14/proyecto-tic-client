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
import uy.um.edu.client.entities.vuelos.EstadoVuelo;
import uy.um.edu.client.entities.vuelos.Vuelo;
import uy.um.edu.client.exceptions.EntidadYaExiste;
import uy.um.edu.client.exceptions.InvalidInformation;
import uy.um.edu.client.services.*;

import java.util.List;


@Controller
public class AdminAerolineaController {
    @Autowired
    private UsuarioAerolineaService usuarioAerolineaService;
    @Autowired
    private AeropuertoService aeropuertoService;
    @Autowired
    private AvionService avionService;
    @Autowired
    private VueloService vueloService;
    @Autowired
    private AerolineaService aerolineaService;
    @FXML
    private TextField txtCodigoVuelo;


    @FXML
    private ChoiceBox<Aeropuerto> choiceBoxAeropuertoOrigen;
    @FXML
    private ChoiceBox<Aeropuerto> choiceBoxAeropuertoDestino;
    @FXML
    private TextField txtFechaSalida;
    @FXML
    private TextField txtFechaLlegada;

    @FXML
    private TextField txtAvion;
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
    private ListView<String> aeropuertosAsociadosListView;

    private AdminAerolinea adminAerolinea;
    private Aerolinea aerolinea;
    private List<Aeropuerto> aeropuertosAsociados;
    public void initialize(){
        this.nombreAerolinea.setText(aerolinea.getNombre());
        this.nombreAdminAerolinea.setText(adminAerolinea.getNombre());

        aeropuertosAsociados = (List<Aeropuerto>) aeropuertoService.obtenerAeropuertosPorAerolinea(aerolinea);

        choiceBoxAeropuertoOrigen.getItems().addAll(aeropuertosAsociados);
        choiceBoxAeropuertoOrigen.getItems().add(null);
        choiceBoxAeropuertoDestino.getItems().addAll(aeropuertosAsociados);
        choiceBoxAeropuertoDestino.getItems().add(null);
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
                 || txtAvion.getText().equals("") || txtHoraSalidaEstimada.getText().equals("")
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
        //String codigoAvion = this.txtAvion.getText();
        //Avion avion = avionMgr.obtenerUnoPorCodigo(codigoAvion);

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
        //vuelo.setAvion(avion);
        vuelo.setEstado(EstadoVuelo.PENDIENTE);
        //guardar en la base de datos
        try {
            vueloService.agregarVuelo(vuelo);
            // Puedes mostrar una confirmación al usuario si lo deseas
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

    public void mostrarAeropuertosAsociadosAction(ActionEvent event ){
        ObservableList<String> aeropuertosObservable = FXCollections.observableArrayList();
        List<Aeropuerto> aeropuertosAsociados = aerolineaService.obtenerAeropuertosAsociados(aerolinea);
        for (Aeropuerto aeropuerto : aeropuertosAsociados)
            aeropuertosObservable.add(aeropuerto.getNombre()+" "+aeropuerto.getCodigo());
        if (aeropuertosAsociados.size() == 0){
            showAlert("No hay aerolineas asociadas", "No hay aerolineas asociadas a este aeropuerto");
        }

        aeropuertosAsociadosListView.setItems(aeropuertosObservable);

    }
    private void limpiarCampos(){
        txtCodigoVuelo.setText("");
        choiceBoxAeropuertoOrigen.setValue(null);
        choiceBoxAeropuertoDestino.setValue(null);
        txtFechaSalida.setText("");
        txtFechaLlegada.setText("");
        txtAvion.setText("");
        txtHoraSalidaEstimada.setText("");
        txtHoraLlegadaEstimada.setText("");
        txtCapacidad.setText("");


    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public void setAdminAerolinea(AdminAerolinea adminAerolinea) {
        this.adminAerolinea = adminAerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
}
