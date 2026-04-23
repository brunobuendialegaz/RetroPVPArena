package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TorneoController {

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
}
