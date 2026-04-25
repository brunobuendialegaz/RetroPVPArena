package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.retropvpadmin.dao.impl.UsuarioDaoImpl;
import org.example.retropvpadmin.dao.interfaces.IUsuarioDao;
import org.example.retropvpadmin.model.Usuario;
import org.example.retropvpadmin.service.AuthService;
import org.example.retropvpadmin.service.Navegacion;
import org.example.retropvpadmin.util.ControlSesion;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private Button logInButton;

    @FXML
    private TextField passField;

    private AuthService service;

    private Navegacion nav;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        actions();
    }

    private void instances() {
        service = new AuthService();
        nav = new Navegacion();
    }

    private void actions() {
        logInButton.setOnAction(event -> {
            String email = emailField.getText();
            String pass = passField.getText();
            Usuario user = service.getDao().checkLogin(email,pass);
            if (user!=null&&user.getTipoUsuario()==1){
                nav.irAPanel(event);
            } else {
                Alert dialogPane = new Alert(Alert.AlertType.ERROR);
                dialogPane.setHeaderText("Error");
                dialogPane.setContentText("Datos de acceso no validos.");
                dialogPane.show();
            }
        });
    }
}
