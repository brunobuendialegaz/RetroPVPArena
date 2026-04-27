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
    private RadioButton finalizadosRadio;

    @FXML
    private TableColumn<?, ?> juegoColumn;

    @FXML
    private ComboBox<?> juegoCombo;

    @FXML
    private TableColumn<?, ?> juezColumn;

    @FXML
    private TableColumn<?, ?> nombreColumn;

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
    private ToggleButton stockButton;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private TableView<?> torneosTableView;

    @FXML
    private ToggleButton userButton;

    @FXML
    private ToggleButton salirButton;

    @FXML
    private Text nombreUser;

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
        torneoDetalleButton.setOnAction(nav::irATorneo);
        torneoButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);
    }
}
