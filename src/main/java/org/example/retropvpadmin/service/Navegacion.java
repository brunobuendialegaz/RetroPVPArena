package org.example.retropvpadmin.service;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.retropvpadmin.MainApplication;
import org.example.retropvpadmin.util.ControlSesion;

import javax.swing.*;
import java.io.IOException;

public class Navegacion {

    private static final String PANELV = "panel-view.fxml";
    private static final String USUARIOV = "usuario-view.fxml";
    private static final String STOCKV = "usuario-view.fxml";
    private static final String TORNEOV = "torneo-view.fxml";
    private static final String TORNEODETV = "torneo-detalle-view.fxml";
    private static final String LOGINV = "login-view.fxml";


    public void irAPanel(ActionEvent event) {
        cambiarScene(event, PANELV, "Panel General");
    }

    public void irAUsuario(ActionEvent event){
        cambiarScene(event,USUARIOV, "Panel Usuario");
    }

    public void irAStock(ActionEvent event){
        cambiarScene(event,STOCKV, "Panel Stock");
    }

    public void irATorneo(ActionEvent event){
        cambiarScene(event,TORNEOV, "Panel Torneo");
    }

    public void irATorneoDet(ActionEvent event){
        cambiarScene(event,TORNEODETV, "Panel Torneo detalle");
    }

    public void irALogin(ActionEvent event){
        ControlSesion.getInstance().setUsuarioActivo(null);
        cambiarScene(event, LOGINV,"Login Panel");
    }

    private void cambiarScene(Event event, String view, String title){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(view));
        try {
            Parent parent = fxmlLoader.load();
            stage.setTitle(title);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            ((Stage)(source.getScene().getWindow())).close();
        } catch (IOException e) {
            System.out.println("Error al cambiar la ventana.");
            e.printStackTrace();
        }

    }
}
