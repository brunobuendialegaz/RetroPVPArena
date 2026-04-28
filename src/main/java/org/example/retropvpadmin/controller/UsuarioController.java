package org.example.retropvpadmin.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.impl.UsuarioDaoImpl;
import org.example.retropvpadmin.dao.interfaces.IUsuarioDao;
import org.example.retropvpadmin.model.Usuario;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;
import org.example.retropvpadmin.util.LanzadorAlertas;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable {

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
    private TextField emailField;

    @FXML
    private TextField nombreField;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private TableView<Usuario> userTableView;

    @FXML
    private TableColumn<Usuario, String> nombreColumn;

    @FXML
    private TableColumn<Usuario, String> emailColumn;

    @FXML
    private TableColumn<Usuario, String> tlfColumn;

    @FXML
    private TableColumn<Usuario, Void> actionCol;

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
    private TextField dniField;

    @FXML
    private ToggleButton salirButton;

    @FXML
    private Text nombreUser;

    @FXML
    private TabPane tabPane;

    @FXML
    private ToggleGroup grupoCrearAdmin;

    @FXML
    private ToggleGroup grupoBuscarAdmin;

    private ObservableList<Usuario> usuarios;

    private FilteredList<Usuario> usuariosFiltrados;

    private SortedList<Usuario> usuarioSortedList;

    private Navegacion nav;

    private IUsuarioDao usuarioDao;

    private LanzadorAlertas lanzadorAlertas;

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
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>(SchemDB.U_NOMBRE));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>(SchemDB.U_EMAIL));
        tlfColumn.setCellValueFactory(new PropertyValueFactory<>(SchemDB.U_TLF));
        usuarios = FXCollections.observableList(usuarioDao.listarUsuarios());
        usuariosFiltrados = usuarios.filtered(p -> true);
        usuarioSortedList = usuariosFiltrados.sorted();
        usuarioSortedList.comparatorProperty().bind(userTableView.comparatorProperty());
        userTableView.setItems(usuarioSortedList);
        grupoCrearAdmin.selectToggle((Toggle) clienteCreateRadio);
    }

    private void instances() {
        nav = new Navegacion();
        usuarioDao = new UsuarioDaoImpl();
        lanzadorAlertas = new LanzadorAlertas();
    }

    private void actions() {
        panelButton.setOnAction(nav::irAPanel);
        userButton.setOnAction(nav::irAUsuario);
        stockButton.setOnAction(nav::irAStock);
        torneoButton.setOnAction(nav::irATorneo);
        torneoDetalleButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);
        crearButton.setOnAction(event -> {
            boolean camposRellenos = grupoCrearAdmin.getSelectedToggle()!=null && !nombreField.getText().isEmpty() && !apellidoField.getText().isEmpty()
                    && !emailField.getText().isEmpty() && !direccionField.getText().isEmpty() && !tlfField.getText().isEmpty() && !dniField.getText().isEmpty();
            int admin = ((RadioButton)(grupoCrearAdmin.getSelectedToggle())).getText().equalsIgnoreCase("Admin") ? 1 : 2;
            // actualizamos usuario
            if (usuarioDao.checkDNI(dniField.getText())==1&&camposRellenos){
                usuarioDao.actualizarUsuario(admin, nombreField.getText(), apellidoField.getText(),
                        emailField.getText(), direccionField.getText(), tlfField.getText(), dniField.getText());
                lanzadorAlertas.lanzarAlerta(3,"Usuario actualizado");
                limpiarCampos();
                // creamos usuario
            } else if (usuarioDao.checkDNI(dniField.getText())==0&&camposRellenos) {
                usuarioDao.crearUsuario(admin, nombreField.getText(), apellidoField.getText(),
                        emailField.getText(), direccionField.getText(), tlfField.getText(), dniField.getText());
                limpiarCampos();
                lanzadorAlertas.lanzarAlerta(3, "Usuario Creado");
                // mensaje de error
            } else {
                lanzadorAlertas.lanzarAlerta(1, "Campos sin rellenar");
            }
            boolean esOno = grupoCrearAdmin.getSelectedToggle()!=null;
        });
        buscadorField.textProperty().addListener((obs, oldVal, newVal) -> aplicarFiltro());
        grupoBuscarAdmin.selectedToggleProperty().addListener((obs, oldVal, newVal) -> aplicarFiltro());
    }

    private void limpiarCampos(){
        nombreField.clear();
        apellidoField.clear();
        emailField.clear();
        direccionField.clear();
        tlfField.clear();
        dniField.clear();
    };

    private void aplicarFiltro() {
        usuariosFiltrados.setPredicate(usuario -> {
            String filtro = buscadorField.getText().toLowerCase();
            boolean coincideBuscador = filtro.isEmpty()
                    || usuario.getNombre().toLowerCase().contains(filtro)
                    || usuario.getEmail().toLowerCase().contains(filtro)
                    || usuario.getTelefono().toLowerCase().contains(filtro);

            RadioButton radioActivo = (RadioButton) grupoBuscarAdmin.getSelectedToggle();
            boolean coincideRadio = radioActivo == null
                    || radioActivo.getText().equalsIgnoreCase("Todos")
                    || usuario.getTipoUsuario() == 1;

            return coincideBuscador && coincideRadio;
        });
    }

}
