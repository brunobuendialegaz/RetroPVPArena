package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.retropvpadmin.service.Navegacion;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable {

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private RadioButton adminCreateRadio;

    @FXML
    private RadioButton adminRadio;

    @FXML
    private TextField apellidoField;

    @FXML
    private TextField buscadorField;

    @FXML
    private RadioButton clienteCreateRadio;

    @FXML
    private Button crearButton;

    @FXML
    private TextField direccionField;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private TextField nombreField;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private TableColumn<?, ?> tlfColumn;

    @FXML
    private TextField tlfField;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private ToggleButton userButton;

    @FXML
    private TableView<?> userTableView;

    @FXML
    private TextField dniField;

    @FXML
    private ToggleButton salirButton;

    private Navegacion nav;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI();
        instances();
        actions();
    }

    private void initGUI() {

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
