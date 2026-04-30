package org.example.retropvpadmin.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.example.retropvpadmin.dao.impl.*;
import org.example.retropvpadmin.dao.interfaces.*;
import org.example.retropvpadmin.model.Enfrentamiento;
import org.example.retropvpadmin.model.Rival;
import org.example.retropvpadmin.model.Torneo;
import org.example.retropvpadmin.model.Usuario;
import org.example.retropvpadmin.model.enums.TopEnum;
import org.example.retropvpadmin.model.enums.TorneoEstadoEnum;
import org.example.retropvpadmin.service.BracketService;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TorneoDetalleController implements Initializable {

    @FXML
    private ToggleButton panelButton;

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
    private Text nombreUser;

    @FXML
    private ComboBox<Torneo> torneoCombo;

    @FXML
    private TextField buscadorField;

    @FXML
    private RadioButton todosRadio;

    @FXML
    private RadioButton finalizadosRadio;

    @FXML
    private RadioButton pendientesRadio;

    @FXML
    private RadioButton enProcesoRadio;

    @FXML
    private ToggleGroup filtroEnfrentamiento;

    @FXML
    private TableView<Enfrentamiento> torneosTableView;

    @FXML
    private TableColumn<Enfrentamiento, String> nombreColumn;

    @FXML
    private TableColumn<Enfrentamiento, String> jugador1Column;

    @FXML
    private TableColumn<Enfrentamiento, String> jugador2Column;

    @FXML
    private TableColumn<Enfrentamiento, String> jugador3Column;

    @FXML
    private TableColumn<Enfrentamiento, String> jugador4Column;

    @FXML
    private TableColumn<Enfrentamiento, String> ganadorColumn;

    @FXML
    private ComboBox<Enfrentamiento> enfrentamientoCombo;

    @FXML
    private ComboBox<Usuario> ganadorCombo;

    @FXML
    private Button ganadorButton, sortearButton;

    private Navegacion nav;
    private ITorneoDao torneoDao;
    private IEnfrentamientoDao enfrentamientoDao;
    private IRivalDao rivalDao;
    private BracketService bracketService;

    private ObservableList<Torneo> torneos;
    private ObservableList<Enfrentamiento> enfrentamientos;
    private FilteredList<Enfrentamiento> enfrentamientosFiltrados;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        initGUI();
        actions();
    }

    private void instances() {
        nav = new Navegacion();
        torneoDao = new TorneoDaoImpl();
        enfrentamientoDao = new EnfrentamientoDaoImpl();
        rivalDao = new RivalDaoImpl();
        bracketService = new BracketService(
                new EnfrentamientoDaoImpl(),
                new RivalDaoImpl()
        );
    }

    private void initGUI() {
        nombreUser.setText(ControlSesion.getInstance().getUsuarioActivo().getNombre()+" "+ControlSesion.getInstance().getUsuarioActivo().getApellido());

        torneos = FXCollections.observableArrayList(torneoDao.listadoTorneos());
        torneoCombo.setItems(torneos);
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

        nombreColumn.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNombre()));
        jugador1Column.setCellValueFactory(d -> getRivalNombre(d.getValue(), 0));
        jugador2Column.setCellValueFactory(d -> getRivalNombre(d.getValue(), 1));
        jugador3Column.setCellValueFactory(d -> getRivalNombre(d.getValue(), 2));
        jugador4Column.setCellValueFactory(d -> getRivalNombre(d.getValue(), 3));
        ganadorColumn.setCellValueFactory(d -> {
            Optional<Rival> ganador = d.getValue().getRivals().stream()
                    .filter(r -> Boolean.TRUE.equals(r.getEsGanador()))
                    .findFirst();
            return new SimpleStringProperty(ganador
                    .map(r -> r.getParticipacion().getUsuario().getNombre()
                            + " " + r.getParticipacion().getUsuario().getApellido())
                    .orElse("-"));
        });

        enfrentamientos = FXCollections.observableArrayList();
        enfrentamientosFiltrados = new FilteredList<>(enfrentamientos, e -> true);
        SortedList<Enfrentamiento> sorted = new SortedList<>(enfrentamientosFiltrados);
        sorted.comparatorProperty().bind(torneosTableView.comparatorProperty());
        torneosTableView.setItems(sorted);

        enfrentamientoCombo.setItems(enfrentamientos);
        enfrentamientoCombo.setCellFactory(cb -> new ListCell<>() {
            @Override protected void updateItem(Enfrentamiento e, boolean empty) {
                super.updateItem(e, empty);
                setText(empty || e == null ? null : e.getNombre());
            }
        });
        enfrentamientoCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Enfrentamiento e, boolean empty) {
                super.updateItem(e, empty);
                setText(empty || e == null ? null : e.getNombre());
            }
        });

        ganadorCombo.setCellFactory(cb -> new ListCell<>() {
            @Override protected void updateItem(Usuario u, boolean empty) {
                super.updateItem(u, empty);
                setText(empty || u == null ? null : u.getNombre() + " " + u.getApellido());
            }
        });
        ganadorCombo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Usuario u, boolean empty) {
                super.updateItem(u, empty);
                setText(empty || u == null ? null : u.getNombre() + " " + u.getApellido());
            }
        });
    }

    private SimpleStringProperty getRivalNombre(Enfrentamiento enf, int indice) {
        List<Rival> lista = new ArrayList<>(enf.getRivals());
        if (indice < lista.size()) {
            Usuario u = lista.get(indice).getParticipacion().getUsuario();
            return new SimpleStringProperty(u.getNombre() + " " + u.getApellido());
        }
        return new SimpleStringProperty("-");
    }


    private void actions() {
        panelButton.setOnAction(nav::irAPanel);
        userButton.setOnAction(nav::irAUsuario);
        stockButton.setOnAction(nav::irAStock);
        torneoButton.setOnAction(nav::irATorneo);
        torneoDetalleButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);

        torneoCombo.valueProperty().addListener((obs, oldVal, torneo) -> {
            if (torneo != null) {
                enfrentamientos.setAll(
                        enfrentamientoDao.listadoEnfrentamientos(torneo.getIdTorneo()));
                enfrentamientoCombo.setValue(null);
                ganadorCombo.setItems(FXCollections.emptyObservableList());
            }
        });

        filtroEnfrentamiento.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == todosRadio)
                enfrentamientosFiltrados.setPredicate(e -> true);
            else if (newVal == finalizadosRadio)
                // "Cuartos" en FXML — agrupa rondas previas y clasificatorias
                enfrentamientosFiltrados.setPredicate(e ->
                        e.getTop() == TopEnum.CUARTOS ||
                                e.getTop() == TopEnum.OCTAVOS ||
                                e.getTop() == TopEnum.DIECISEISAVOS ||
                                e.getTop() == TopEnum.PREVIA);
            else if (newVal == enProcesoRadio)
                // "Semis" en FXML
                enfrentamientosFiltrados.setPredicate(e ->
                        e.getTop() == TopEnum.SEMIS);
            else if (newVal == pendientesRadio)
                // "Final" en FXML
                enfrentamientosFiltrados.setPredicate(e ->
                        e.getTop() == TopEnum.FINAL);
        });

        buscadorField.textProperty().addListener((obs, oldVal, newVal) ->
                enfrentamientosFiltrados.setPredicate(e ->
                        newVal.isBlank() ||
                                e.getNombre().toLowerCase().contains(newVal.toLowerCase()) ||
                                e.getRivals().stream().anyMatch(r ->
                                        r.getParticipacion().getUsuario().getNombre()
                                                .toLowerCase().contains(newVal.toLowerCase()))
                )
        );

        enfrentamientoCombo.valueProperty().addListener((obs, oldVal, enf) -> {
            if (enf != null) {
                ObservableList<Usuario> rivales = FXCollections.observableArrayList(
                        enf.getRivals().stream()
                                .map(r -> r.getParticipacion().getUsuario())
                                .toList()
                );
                ganadorCombo.setItems(rivales);
                ganadorCombo.setValue(null);
            }
        });

        ganadorButton.setOnAction(e -> {
            Torneo torneo = torneoCombo.getValue();
            Enfrentamiento enf = enfrentamientoCombo.getValue();
            Usuario ganador = ganadorCombo.getValue();
            if (torneo == null || enf == null || ganador == null) return;

            boolean ok = bracketService.definirGanador(
                    (int) ganador.getIdUsuario(),
                    torneo.getIdTorneo(),
                    enf.getIdEnfrentamiento()
            );
            if (ok) {
                // Si es la final, el torneo ha terminado
                if (enf.getTop() == TopEnum.FINAL) {
                    torneoDao.actualizarEstado(
                            torneo.getIdTorneo(), TorneoEstadoEnum.TERMINADO);
                    torneos.setAll(torneoDao.listadoTorneos());
                }
                enfrentamientos.setAll(
                        enfrentamientoDao.listadoEnfrentamientos(torneo.getIdTorneo()));
                enfrentamientoCombo.setValue(null);
                ganadorCombo.setItems(FXCollections.emptyObservableList());
            }
        });

        sortearButton.setOnAction(e -> {
            Torneo torneo = torneoCombo.getValue();
            if (torneo == null) return;

            List<Integer> idsGanadores = enfrentamientos.stream()
                    .flatMap(enf -> enf.getRivals().stream())
                    .filter(r -> Boolean.TRUE.equals(r.getEsGanador()))
                    .map(r -> r.getId().getIdUsuario())
                    .toList();

            if (idsGanadores.isEmpty()) return;

            boolean ok = bracketService.sortearSiguienteRonda(
                    torneo.getIdTorneo(), idsGanadores);

            if (ok) {
                enfrentamientos.setAll(
                        enfrentamientoDao.listadoEnfrentamientos(torneo.getIdTorneo()));
            }
        });


    }
}
