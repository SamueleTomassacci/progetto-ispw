package it.uniroma2.dicii.ispw.utils.factory;

import javafx.scene.control.Alert;

public class Error implements DialogBox{
    @Override public void showBox(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore!");
        alert.setHeaderText(message);
        alert.showAndWait();

    }
}
