package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StockController {
    @FXML
    private RadioButton accesorioRadio;

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private TextField buscadorField;

    @FXML
    private TextField consolaIdField;

    @FXML
    private RadioButton consolaRadio;

    @FXML
    private Button crearButton;

    @FXML
    private TextArea descripcionField;

    @FXML
    private RadioButton disponibleRadio;

    @FXML
    private TextField fechaField;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private RadioButton juegoRadio;

    @FXML
    private TextField jugadoresField;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private TextField nombreField;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private TextField precioField;

    @FXML
    private TableView<?> productosTableView;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private TableColumn<?, ?> stockColumn;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private ToggleButton userButton;
}
