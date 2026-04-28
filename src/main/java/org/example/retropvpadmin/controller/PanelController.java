package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import org.example.retropvpadmin.dao.impl.StockDaoImpl;
import org.example.retropvpadmin.dao.impl.TorneoDaoImpl;
import org.example.retropvpadmin.dao.impl.UsuarioDaoImpl;
import org.example.retropvpadmin.dao.interfaces.IStockDao;
import org.example.retropvpadmin.dao.interfaces.ITorneoDao;
import org.example.retropvpadmin.dao.interfaces.IUsuarioDao;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelController implements Initializable {

    @FXML
    private Text articlesText;

    @FXML
    private ToggleButton panelButton;

    @FXML
    private ToggleButton stockButton;

    @FXML
    private Text stockText;

    @FXML
    private ToggleButton torneoButton;

    @FXML
    private Text torneoText;

    @FXML
    private ToggleButton userButton;

    @FXML
    private Text usersText;

    @FXML
    private ToggleButton torneoDetalleButton;

    @FXML
    private ToggleButton salirButton;

    @FXML
    private Text nombreUser;

    private Navegacion nav;

    private IUsuarioDao usuarioDao;

    private IStockDao stockDao;

    private ITorneoDao torneoDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        initGUI();
        actions();
    }

    private void initGUI() {
        nombreUser.setText(ControlSesion.getInstance().getUsuarioActivo().getNombre()+" "+ControlSesion.getInstance().getUsuarioActivo().getApellido());
        usersText.setText(String.valueOf(usuarioDao.totalUsuarios()));
        articlesText.setText(String.valueOf(stockDao.articulosCatalogo()));
        stockText.setText(String.valueOf(stockDao.articulosStockBajo()));
        torneoText.setText(String.valueOf(torneoDao.torneosProgramados()));
    }

    private void instances() {
        usuarioDao = new UsuarioDaoImpl();
        stockDao = new StockDaoImpl();
        torneoDao = new TorneoDaoImpl();
        nav = new Navegacion();
    }

    private void actions() {
        panelButton.setOnAction(nav::irAPanel);
        userButton.setOnAction(nav::irAUsuario);
        stockButton.setOnAction(nav::irAStock);
        torneoButton.setOnAction(nav::irATorneo);
        torneoDetalleButton.setOnAction(nav::irATorneoDet);
        salirButton.setOnAction(nav::irALogin);
    }
}
