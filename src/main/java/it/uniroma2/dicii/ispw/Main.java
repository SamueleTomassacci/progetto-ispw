package it.uniroma2.dicii.ispw;

import it.uniroma2.dicii.ispw.utils.ChangePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/loginPage1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 760);
        ChangePage istanza=ChangePage.getChangePage();
        istanza.setStage(stage);
        stage.setTitle("PlayBasket!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}