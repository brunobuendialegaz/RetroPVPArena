package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    @FXML
    private RadioButton accesorioRadio;

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private TextField buscadorField;

    @FXML
    private ComboBox<?> consolaCombo;

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
