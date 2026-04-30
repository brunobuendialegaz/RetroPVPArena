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
import org.example.retropvpadmin.dao.impl.*;
import org.example.retropvpadmin.dao.interfaces.*;
import org.example.retropvpadmin.model.*;
import org.example.retropvpadmin.model.enums.TorneoEstadoEnum;
import org.example.retropvpadmin.service.BracketService;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TorneoController implements Initializable {

    @FXML
    private ToggleButton userButton;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private ToggleButton salirButton;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private Text nombreUser;

    @FXML
    private TextField buscadorField;

    @FXML
    private TextField filtroUsuario;

    @FXML
    private TextField nombreCrearField;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private RadioButton finalizadosRadio;

    @FXML
    private RadioButton pendientesRadio;

    @FXML
    private RadioButton enProcesoRadio;

    @FXML
    private ToggleGroup filtroTorneos;

    @FXML
    private TableView<Torneo> torneosTableView;

    @FXML
    private TableColumn<Torneo, String> nombreColumn;

    @FXML
    private TableColumn<Torneo, String> juegoColumn;

    @FXML
    private TableColumn<Torneo, String> salaColumn;

    @FXML
    private TableColumn<Torneo, String> juezColumn;

    @FXML
    private TableColumn<Torneo, String> fechaColumn;

    @FXML
    private TableColumn<Torneo, String> estadoColumn;

    @FXML
    private ComboBox<Juego> juegoCombo;

    @FXML
    private ComboBox<Sala> salaCombo;

    @FXML
    private ComboBox<Usuario> arbitroCombo;

    @FXML
    private Spinner<Integer> participantesSpiner;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private Button crearButton;

    @FXML
    private Button añadirParticipanteBtn;

    @FXML
    private Button bracketButton;

    @FXML
    private ComboBox<Torneo> torneoCombo;

    @FXML
    private TableView<Usuario> tablaUsers;

    @FXML
    private TableView<Usuario> tablaParticipante;

    @FXML
    private TableColumn<Usuario, String> idColUsers;

    @FXML
    private TableColumn<Usuario, String> nombreColUsers;

    @FXML
    private TableColumn<Usuario, String> idColParticipante;

    @FXML
    private TableColumn<Usuario, String> nombreColParticipante;

    private Navegacion nav;
    private ITorneoDao torneoDao;
    private IUsuarioDao usuarioDao;
    private IParticipacionDao participacionDao;
    private ISalaDao salaDao;
    private IArticuloDao articuloDao;
    private BracketService bracketService;

    private ObservableList<Torneo> torneos;
    private ObservableList<Torneo> torneosCreados;
    private ObservableList<Usuario> jugadores;
    private ObservableList<Usuario> participantes;
    private FilteredList<Torneo> torneosFiltrados;
    private FilteredList<Usuario> jugadoresFiltrados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        initGUI();
        actions();
    }

    private void instances() {
        nav = new Navegacion();
        torneoDao = new TorneoDaoImpl();
        usuarioDao = new UsuarioDaoImpl();
        participacionDao = new ParticipacionDaoImpl();
        salaDao = new SalaDaoImpl();
        articuloDao = new ArticuloDaoImpl();
        bracketService = new BracketService(new EnfrentamientoDaoImpl(), new RivalDaoImpl());
    }

    private void initGUI() {
        nombreUser.setText(ControlSesion.getInstance().getUsuarioActivo().getNombre()+" "+ControlSesion.getInstance().getUsuarioActivo().getApellido());
        nombreColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNombre()));
        juegoColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getJuego().getNombre()));
        salaColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getSala().getNombre()));
        juezColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getUsuario().getNombre()
                        + " " + d.getValue().getUsuario().getApellido()));
        fechaColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getFecha().toString()));
        estadoColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getTorneoEstadoEnum().getValorDB()));

        torneos = FXCollections.observableArrayList(torneoDao.listadoTorneos());
        torneosFiltrados = new FilteredList<>(torneos, t -> true);
        SortedList<Torneo> torneosSorted = new SortedList<>(torneosFiltrados);
        torneosSorted.comparatorProperty().bind(torneosTableView.comparatorProperty());
        torneosTableView.setItems(torneosSorted);

        ObservableList<Articulo> articulos =
                FXCollections.observableArrayList(articuloDao.listadoArticulos());
        ObservableList<Juego> juegos = FXCollections.observableArrayList(
                articulos.stream()
                        .filter(a -> a.getJuego() != null)
                        .map(Articulo::getJuego)
                        .toList()
        );
        juegoCombo.setItems(juegos);
        juegoCombo.setCellFactory(cb -> new ListCell<>() {
            @Override protected void updateItem(Juego j, boolean empty) {
                super.updateItem(j, empty);
                setText(empty || j == null ? null : j.getNombre());
            }
        });
        juegoCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Juego j, boolean empty) {
                super.updateItem(j, empty);
                setText(empty || j == null ? null : j.getNombre());
            }
        });

        ObservableList<Sala> salas =
                FXCollections.observableArrayList(salaDao.listarSalas());
        salaCombo.setItems(salas);
        salaCombo.setCellFactory(cb -> new ListCell<>() {
            @Override protected void updateItem(Sala s, boolean empty) {
                super.updateItem(s, empty);
                setText(empty || s == null ? null : s.getNombre());
            }
        });
        salaCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Sala s, boolean empty) {
                super.updateItem(s, empty);
                setText(empty || s == null ? null : s.getNombre());
            }
        });

        ObservableList<Usuario> admins = FXCollections.observableArrayList(
                usuarioDao.listarUsuarios().stream()
                        .filter(u -> u.getTipoUsuario() == 1)
                        .toList()
        );
        arbitroCombo.setItems(admins);
        arbitroCombo.setCellFactory(cb -> new ListCell<>() {
            @Override protected void updateItem(Usuario u, boolean empty) {
                super.updateItem(u, empty);
                setText(empty || u == null ? null : u.getNombre() + " " + u.getApellido());
            }
        });
        arbitroCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Usuario u, boolean empty) {
                super.updateItem(u, empty);
                setText(empty || u == null ? null : u.getNombre() + " " + u.getApellido());
            }
        });

        participantesSpiner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 64, 8));

        torneosCreados = FXCollections.observableArrayList(
                torneos.stream()
                        .filter(t -> t.getTorneoEstadoEnum() == TorneoEstadoEnum.CREADO)
                        .toList()
        );
        torneoCombo.setItems(torneosCreados);
        torneoCombo.setCellFactory(cb -> new ListCell<>() {
            @Override protected void updateItem(Torneo t, boolean empty) {
                super.updateItem(t, empty);
                setText(empty || t == null ? null : t.getNombre());
            }
        });
        torneoCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Torneo t, boolean empty) {
                super.updateItem(t, empty);
                setText(empty || t == null ? null : t.getNombre());
            }
        });

        idColUsers.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getIdUsuario())));
        nombreColUsers.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNombre() + " " + d.getValue().getApellido()));
        idColParticipante.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getIdUsuario())));
        nombreColParticipante.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNombre() + " " + d.getValue().getApellido()));

        jugadores = FXCollections.observableArrayList(usuarioDao.listarJugadores());
        jugadoresFiltrados = new FilteredList<>(jugadores, u -> true);
        tablaUsers.setItems(jugadoresFiltrados);

        participantes = FXCollections.observableArrayList();
        tablaParticipante.setItems(participantes);
    }

    private void actions() { // todo Meter Avisos!!
        panelButton.setOnAction(nav::irAPanel);
        userButton.setOnAction(nav::irAUsuario);
        stockButton.setOnAction(nav::irAStock);
        torneoButton.setOnAction(nav::irATorneo);
        torneoDetalleButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);

        buscadorField.textProperty().addListener((obs, oldVal, newVal) ->
                torneosFiltrados.setPredicate(t ->
                        newVal.isBlank() ||
                                t.getNombre().toLowerCase().contains(newVal.toLowerCase()) ||
                                t.getJuego().getNombre().toLowerCase().contains(newVal.toLowerCase())
                )
        );

        filtroTorneos.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == todosRadio)
                torneosFiltrados.setPredicate(t -> true);
            else if (newVal == finalizadosRadio)
                torneosFiltrados.setPredicate(t ->
                        t.getTorneoEstadoEnum() == TorneoEstadoEnum.TERMINADO);
            else if (newVal == pendientesRadio)
                torneosFiltrados.setPredicate(t ->
                        t.getTorneoEstadoEnum() == TorneoEstadoEnum.CREADO);
            else if (newVal == enProcesoRadio)
                torneosFiltrados.setPredicate(t ->
                        t.getTorneoEstadoEnum() == TorneoEstadoEnum.INICIADO);
        });

        filtroUsuario.textProperty().addListener((obs, oldVal, newVal) ->
                jugadoresFiltrados.setPredicate(u ->
                        newVal.isBlank() ||
                                u.getNombre().toLowerCase().contains(newVal.toLowerCase()) ||
                                u.getApellido().toLowerCase().contains(newVal.toLowerCase())
                )
        );

        torneoCombo.valueProperty().addListener((obs, oldVal, torneo) -> {
            if (torneo != null) {
                participantes.setAll(
                        participacionDao.listadoParticipantes(torneo.getIdTorneo()));
                actualizarFiltroJugadores();
            }
        });

        añadirParticipanteBtn.setOnAction(e -> {
            Torneo torneoSel = torneoCombo.getValue();
            Usuario usuarioSel = tablaUsers.getSelectionModel().getSelectedItem();
            if (torneoSel == null || usuarioSel == null) return;

            boolean ok = participacionDao.anadirParticipante(
                    (int) usuarioSel.getIdUsuario(), torneoSel.getIdTorneo());
            if (ok) {
                participantes.add(usuarioSel);
                actualizarFiltroJugadores();
            }
        });

        bracketButton.setOnAction(e -> {
            Torneo torneoSel = torneoCombo.getValue();
            if (torneoSel == null || participantes.isEmpty()) return;

            List<Integer> ids = participantes.stream()
                    .map(u -> (int) u.getIdUsuario())
                    .toList();

            boolean ok = bracketService.generarBracket(torneoSel.getIdTorneo(), ids);
            if (ok) {
                // Actualizar estado del torneo a INICIADO
                torneoDao.actualizarEstado(
                        torneoSel.getIdTorneo(), TorneoEstadoEnum.INICIADO);
                // Refrescar listas
                torneos.setAll(torneoDao.listadoTorneos());
                torneosCreados.setAll(
                        torneos.stream()
                                .filter(t -> t.getTorneoEstadoEnum() == TorneoEstadoEnum.CREADO)
                                .toList()
                );
                torneoCombo.setValue(null);
                participantes.clear();
            }
        });

        crearButton.setOnAction(e -> {
            if (nombreCrearField.getText().isBlank()
                    || juegoCombo.getValue() == null
                    || salaCombo.getValue() == null
                    || arbitroCombo.getValue() == null
                    || fechaPicker.getValue() == null) return;

            Torneo nuevo = new Torneo();
            nuevo.setNombre(nombreCrearField.getText().trim());
            nuevo.setJuego(juegoCombo.getValue());
            nuevo.setSala(salaCombo.getValue());
            nuevo.setUsuario(arbitroCombo.getValue());
            nuevo.setFecha(Date.valueOf(fechaPicker.getValue()));
            nuevo.setTorneoEstadoEnum(TorneoEstadoEnum.CREADO);

            boolean ok = torneoDao.crearTorneo(nuevo);
            if (ok) {
                torneos.setAll(torneoDao.listadoTorneos());
                torneosCreados.setAll(
                        torneos.stream()
                                .filter(t -> t.getTorneoEstadoEnum() == TorneoEstadoEnum.CREADO)
                                .toList()
                );
                // Limpiar formulario
                nombreCrearField.clear();
                juegoCombo.setValue(null);
                salaCombo.setValue(null);
                arbitroCombo.setValue(null);
                fechaPicker.setValue(null);
            }
        });
    }

    private void actualizarFiltroJugadores() {
        jugadoresFiltrados.setPredicate(u ->
                participantes.stream()
                        .noneMatch(p -> p.getIdUsuario() == u.getIdUsuario())
        );
    }
}
