package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ResourceBundle;

public class TorneoDetalleController implements Initializable {

    @FXML
    private TextField buscadorField;

    @FXML
    private RadioButton enProcesoRadio;

    @FXML
    private RadioButton finalizadosRadio;

    @FXML
    private Button ganadorButton;

    @FXML
    private TableColumn<?, ?> ganadorColumn;

    @FXML
    private ComboBox<?> ganadorCombo;

    @FXML
    private TableColumn<?, ?> jugador1Column;

    @FXML
    private TableColumn<?, ?> jugador2Column;

    @FXML
    private TableColumn<?, ?> jugador3Column;

    @FXML
    private TableColumn<?, ?> jugador4Column;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private RadioButton pendientesRadio;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private ComboBox<?> torneoCombo;

    @FXML
    private TableView<?> torneosTableView;

    @FXML
    private ToggleButton userButton;

    @FXML
    private ComboBox<?> enfrentamientoCombo;

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
