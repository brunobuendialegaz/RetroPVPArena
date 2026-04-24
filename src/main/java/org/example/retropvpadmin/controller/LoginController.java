package org.example.retropvpadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.retropvpadmin.dao.impl.UsuarioDaoImpl;
import org.example.retropvpadmin.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private Button logInButton;

    @FXML
    private TextField passField;

    private UsuarioDaoImpl usuarioDao;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instances();
        actions();
    }

    private void instances() {
        usuarioDao = new UsuarioDaoImpl();
    }

    private void actions() {
        logInButton.setOnAction(event -> {
            String email = emailField.getText();
            String pass = passField.getText();
            Usuario user = usuarioDao.checkLogin(email,pass);
            if (user!=null){
                System.out.println("Login correcto");
                System.out.println(user.getNombre());
                System.out.println(user.getApellido());
                System.out.println(user.getDNI());
                System.out.println(user.getEmail());
                System.out.println(user.getNombre());
            } else {
                System.out.println("Datos de acceso incorrectos");
            }
        });
    }
}
