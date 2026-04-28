package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.example.retropvpadmin.model.Articulo;
import org.example.retropvpadmin.model.Consola;
import org.example.retropvpadmin.model.Usuario;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    @FXML
    private RadioButton accesorioRadio;

    @FXML
    private TableColumn<Articulo, Void> actionCol;

    @FXML
    private TextField buscadorField;

    @FXML
    private ComboBox<Consola> consolaCombo;

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
    private TableColumn<Articulo, String> idColumn;

    @FXML
    private RadioButton juegoRadio;

    @FXML
    private TextField jugadoresField;

    @FXML
    private TableColumn<Articulo, String> nombreColumn;

    @FXML
    private TextField nombreField;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private TextField precioField;

    @FXML
    private TableView<Articulo> productosTableView;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private TableColumn<Articulo, String> stockColumn;

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
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Modificar");
            {
                btn.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());
                    tabPane.getSelectionModel().select(1);
                    nombreField.setText(usuario.getNombre());
                    apellidoField.setText(usuario.getApellido());
                    emailField.setText(usuario.getEmail());
                    direccionField.setText(usuario.getDireccion());
                    tlfField.setText(usuario.getTelefono());
                    dniField.setText(usuario.getDNI());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
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
