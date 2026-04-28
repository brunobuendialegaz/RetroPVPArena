package org.example.retropvpadmin.controller;

import javafx.beans.property.SimpleStringProperty;
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
import org.example.retropvpadmin.dao.impl.ArticuloDaoImpl;
import org.example.retropvpadmin.dao.interfaces.IArticuloDao;
import org.example.retropvpadmin.model.Articulo;
import org.example.retropvpadmin.model.Consola;
import org.example.retropvpadmin.model.Stock;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;
import org.example.retropvpadmin.util.LanzadorAlertas;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class StockController implements Initializable {
    @FXML
    private RadioButton accesorioRadio;

    @FXML
    private TableColumn<Articulo, Void> actionCol;

    @FXML
    private TextField buscadorField;

    @FXML
    private ComboBox<Articulo> consolaCombo;

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

    @FXML
    private Spinner<Integer> cantidadSpinner;

    @FXML
    private TabPane tabPane;

    @FXML
    private ToggleGroup todosFiltro;

    @FXML
    private ToggleGroup tipoArticulo;

    @FXML
    private TextField idArticuloField;

    private Navegacion nav;

    private IArticuloDao articuloDao;

    private ObservableList<Articulo> articulos;

    private ObservableList<Articulo> consolas;

    private FilteredList<Articulo> articuloFilteredList;

    private SortedList<Articulo> articuloSortedList;

    private LanzadorAlertas lanzadorAlertas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        initGUI();
        actions();
    }

    private void initGUI() {
        nombreUser.setText(ControlSesion.getInstance().getUsuarioActivo().getNombre()+" "+ControlSesion.getInstance().getUsuarioActivo().getApellido());
        cantidadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Modificar");
            {
                btn.setOnAction(event -> {
                    limpiarCampos();
                    Articulo articulo = getTableView().getItems().get(getIndex());
                    tabPane.getSelectionModel().select(1);
                    idArticuloField.setText(String.valueOf(articulo.getIdArticulo()));
                    nombreField.setText(articulo.getNombre());
                    descripcionField.setText(articulo.getDescription());
                    if (articulo.getConsola() != null){
                        fechaField.setText(String.valueOf(articulo.getConsola().getAnioLanzamiento()));
                        tipoArticulo.selectToggle(consolaRadio);
                    } else if (articulo.getJuego() != null) {
                        fechaField.setText(String.valueOf(articulo.getJuego().getAnioLanzamiento()));
                        tipoArticulo.selectToggle(juegoRadio);
                        jugadoresField.setText(String.valueOf(articulo.getJuego().getJugadoresPvp()));
                        int idConsolaDelJuego = articulo.getJuego().getConsola().getIdArticulo();
                        consolas.stream()
                                .filter(a -> a.getIdArticulo().equals((Integer) idConsolaDelJuego))
                                .findFirst()
                                .ifPresent(consolaCombo::setValue);
                    } else if (articulo.getAccesorio() != null) {
                        tipoArticulo.selectToggle(accesorioRadio);
                        int idConsolaDelAccesorio = articulo.getAccesorio().getConsola().getIdArticulo();
                        consolas.stream()
                                .filter(a -> a.getIdArticulo().equals((Integer) idConsolaDelAccesorio))
                                .findFirst()
                                .ifPresent(consolaCombo::setValue);
                    }
                    precioField.setText(String.valueOf(articulo.getPrecio()));
                    articulo.getStocks().stream()
                            .findFirst()
                            .ifPresent(stock -> cantidadSpinner.getValueFactory().setValue(stock.getCantidad()));
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
        idColumn.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getIdArticulo()))
        );
        nombreColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombre())
        );
        stockColumn.setCellValueFactory(data -> {
            Articulo articulo = data.getValue();
            Set<Stock> stocks = articulo.getStocks();
            if (stocks != null && !stocks.isEmpty()) {
                int cantidad = stocks.iterator().next().getCantidad();
                return new SimpleStringProperty(String.valueOf(cantidad));
            }
            return new SimpleStringProperty("0");
        });
        articulos = FXCollections.observableList(articuloDao.listadoArticulos());
        consolas = FXCollections.observableArrayList(
                articulos.stream()
                        .filter(a -> a.getConsola() != null)
                        .toList()
        );
        articuloFilteredList = articulos.filtered(p -> true);
        articuloSortedList = articuloFilteredList.sorted();
        articuloSortedList.comparatorProperty().bind(productosTableView.comparatorProperty());
        productosTableView.setItems(articuloSortedList);
        tipoArticulo.selectToggle((Toggle) consolaRadio);
        consolaCombo.setItems(consolas);
        consolaCombo.setCellFactory(cb -> new ListCell<Articulo>() {
            @Override
            protected void updateItem(Articulo articulo, boolean empty) {
                super.updateItem(articulo, empty);
                setText(empty || articulo == null ? null : articulo.getNombre());
            }
        });
        consolaCombo.setButtonCell(new ListCell<Articulo>() {
            @Override
            protected void updateItem(Articulo articulo, boolean empty) {
                super.updateItem(articulo, empty);
                setText(empty || articulo == null ? null : articulo.getNombre());
            }
        });
    }

    private void instances() {
        nav = new Navegacion();
        articuloDao = new ArticuloDaoImpl();
        lanzadorAlertas = new LanzadorAlertas();
    }

    private void actions() {
        panelButton.setOnAction(nav::irAPanel);
        userButton.setOnAction(nav::irAUsuario);
        stockButton.setOnAction(nav::irAStock);
        torneoButton.setOnAction(nav::irATorneo);
        torneoDetalleButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);
        buscadorField.textProperty().addListener((obs, oldVal, newVal) -> aplicarFiltro());
        todosFiltro.selectedToggleProperty().addListener((obs, oldVal, newVal) -> aplicarFiltro());
        crearButton.setOnAction(event -> {
            boolean camposRConsola = tipoArticulo.getSelectedToggle()!=null && !nombreField.getText().isEmpty() && !precioField.getText().isEmpty();
            boolean camposRJuegoAccesorio = camposRConsola && consolaCombo.getSelectionModel().getSelectedItem()!=null;
            int existeArticulo = 0;
            if (!idArticuloField.getText().isEmpty()){
                existeArticulo = articuloDao.checkIdArticulo(Integer.parseInt(idArticuloField.getText()));
            }
            switch (existeArticulo){
                case 1 -> {
                    if (consolaRadio.isSelected()&&camposRConsola){
                        articuloDao.updateConsola(idArticuloField.getText(), nombreField.getText(), precioField.getText(), descripcionField.getText(),
                                cantidadSpinner.getValue(), Integer.parseInt(fechaField.getText()));
                        lanzadorAlertas.lanzarAlerta(3,"Articulo actualizado");
                        limpiarCampos();
                    } else if (juegoRadio.isSelected()&&camposRJuegoAccesorio) {
                        articuloDao.updateJuego(idArticuloField.getText(), nombreField.getText(), precioField.getText(), descripcionField.getText(),
                                cantidadSpinner.getValue(), consolaCombo.getSelectionModel().getSelectedItem().getIdArticulo(), Integer.parseInt(fechaField.getText()), Integer.parseInt(jugadoresField.getText()));
                        lanzadorAlertas.lanzarAlerta(3,"Articulo actualizado");
                        limpiarCampos();
                    } else if (accesorioRadio.isSelected()&&camposRJuegoAccesorio) {
                        articuloDao.updateAccesorio(idArticuloField.getText(), nombreField.getText(), precioField.getText(), descripcionField.getText(),
                                cantidadSpinner.getValue(), consolaCombo.getSelectionModel().getSelectedItem().getIdArticulo());
                        lanzadorAlertas.lanzarAlerta(3,"Articulo actualizado");
                        limpiarCampos();
                    } else {
                        lanzadorAlertas.lanzarAlerta(1, "Campos sin rellenar");
                    }
                }
                case 0 -> {
                    if (consolaRadio.isSelected()&&camposRConsola){
                        articuloDao.crearConsola(nombreField.getText(), precioField.getText(), descripcionField.getText(),
                                cantidadSpinner.getValue(), Integer.parseInt(fechaField.getText()));
                        lanzadorAlertas.lanzarAlerta(3,"Articulo Creado");
                        limpiarCampos();
                    } else if (juegoRadio.isSelected()&&camposRJuegoAccesorio) {
                        articuloDao.crearJuego(nombreField.getText(), precioField.getText(), descripcionField.getText(),
                                cantidadSpinner.getValue(), consolaCombo.getSelectionModel().getSelectedItem().getIdArticulo(), Integer.parseInt(fechaField.getText()), Integer.parseInt(jugadoresField.getText()));
                        lanzadorAlertas.lanzarAlerta(3,"Articulo Creado");
                        limpiarCampos();
                    } else if (accesorioRadio.isSelected()&&camposRJuegoAccesorio) {
                        articuloDao.crearAccesorio(nombreField.getText(), precioField.getText(), descripcionField.getText(),
                                cantidadSpinner.getValue(), consolaCombo.getSelectionModel().getSelectedItem().getIdArticulo());
                        lanzadorAlertas.lanzarAlerta(3,"Articulo Creado");
                        limpiarCampos();
                    } else {
                        lanzadorAlertas.lanzarAlerta(1, "Campos sin rellenar");
                    }
                }
            }
        });
    }

    private void limpiarCampos(){
        nombreField.clear();
        descripcionField.clear();
        fechaField.clear();
        consolaCombo.getSelectionModel().select(0);
        jugadoresField.clear();
        precioField.clear();
        cantidadSpinner.getValueFactory().setValue(0);
    }

    private void aplicarFiltro() {
        articuloFilteredList.setPredicate(articulo -> {
            String filtro = buscadorField.getText().toLowerCase();
            boolean coincideBuscador = filtro.isEmpty()
                    || articulo.getNombre().toLowerCase().contains(filtro);
            RadioButton radioActivo = (RadioButton) todosFiltro.getSelectedToggle();
            boolean coincideRadio = true;
            if (radioActivo != null && radioActivo.getText().equalsIgnoreCase("Disponible")) {
                coincideRadio = articulo.getStocks() != null
                        && !articulo.getStocks().isEmpty()
                        && articulo.getStocks().iterator().next().getCantidad() > 0;
            }
            return coincideBuscador && coincideRadio;
        });
    }
}
