package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TorneoDetalleController {

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

}
