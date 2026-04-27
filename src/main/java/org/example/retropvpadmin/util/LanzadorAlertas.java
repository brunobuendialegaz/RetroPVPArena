package org.example.retropvpadmin.util;

import javafx.scene.control.Alert;

public class LanzadorAlertas {

    public void lanzarAlerta(int tipo, String text){
        Alert dialogPane = new Alert(Alert.AlertType.ERROR);
        switch (tipo){
            case 1 -> {
                dialogPane.setHeaderText("Error");
            }
            case 2 -> {
                dialogPane.setAlertType(Alert.AlertType.WARNING);
                dialogPane.setHeaderText("Alerta");
            }
            case 3 -> {
                dialogPane.setAlertType(Alert.AlertType.CONFIRMATION);
                dialogPane.setHeaderText("Acción realizada");
            }
            default -> {
                dialogPane.setAlertType(Alert.AlertType.INFORMATION);
                dialogPane.setHeaderText("Info");
            }
        }
        dialogPane.setContentText(text);
        dialogPane.show();
    }
}
