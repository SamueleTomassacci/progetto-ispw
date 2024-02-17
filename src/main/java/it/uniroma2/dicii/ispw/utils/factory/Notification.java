package it.uniroma2.dicii.ispw.utils.factory;

import javafx.scene.control.Alert;

public class Notification implements DialogBox{
    @Override
    public void showBox(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione!");
        alert.setHeaderText(message);
        alert.showAndWait();

    }
}
