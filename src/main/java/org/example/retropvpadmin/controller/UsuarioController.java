package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UsuarioController {

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
    private ToggleButton userButton;

    @FXML
    private TableView<?> userTableView;
}
