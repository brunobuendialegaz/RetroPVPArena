package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ResourceBundle;

public class TorneoController implements Initializable {

    @FXML
    private ComboBox<?> arbitroCombo;

    @FXML
    private Button añadirParticipanteBtn;

    @FXML
    private TextField buscadorField;

    @FXML
    private Button crearButton;

    @FXML
    private RadioButton enProcesoRadio;

    @FXML
    private TableColumn<?, ?> estadoColumn;

    @FXML
    private TableColumn<?, ?> fechaColumn;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private ToggleGroup filtroTorneos;

    @FXML
    private TextField filtroUsuario;

    @FXML
    private RadioButton finalizadosRadio;

    @FXML
    private TableColumn<?, ?> idColParticipante;

    @FXML
    private TableColumn<?, ?> idColUsers;

    @FXML
    private TableColumn<?, ?> juegoColumn;

    @FXML
    private ComboBox<?> juegoCombo;

    @FXML
    private TableColumn<?, ?> juezColumn;

    @FXML
    private TableColumn<?, ?> nombreColParticipante;

    @FXML
    private TableColumn<?, ?> nombreColUsers;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private Text nombreUser;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private Spinner<?> participantesSpiner;

    @FXML
    private RadioButton pendientesRadio;

    @FXML
    private TableColumn<?, ?> salaColumn;

    @FXML
    private ComboBox<?> salaCombo;

    @FXML
    private ToggleButton salirButton;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private TableView<?> tablaParticipante;

    @FXML
    private TableView<?> tablaUsers;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private ComboBox<?> torneoCombo;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private TableView<?> torneosTableView;

    @FXML
    private ToggleButton userButton;



    private Navegacion nav;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        initGUI();
        actions();
    }

    private void initGUI() {
        nombreUser.setText(ControlSesion.getInstance().getUsuarioActivo().getNombre()+" "+ControlSesion.getInstance().getUsuarioActivo().getApellido());
    }

    private void instances() {
        nav = new Navegacion();
    }

    private void actions() {
        panelButton.setOnAction(nav::irAPanel);
        userButton.setOnAction(nav::irAUsuario);
        stockButton.setOnAction(nav::irAStock);
        torneoButton.setOnAction(nav::irATorneo);
        torneoDetalleButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);
    }
}
