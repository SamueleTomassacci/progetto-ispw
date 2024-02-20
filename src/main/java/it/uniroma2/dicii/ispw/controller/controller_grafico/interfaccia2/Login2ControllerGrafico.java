package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;

import it.uniroma2.dicii.ispw.utils.bean.Role;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.RuoloNonSelezionatoException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login2ControllerGrafico implements Initializable{
    @FXML
    private ListView<String> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista.getItems().addAll("Giocatore", "Proprietario", "Gestore");

    }

    public void avanti() {
        CredentialsBean cred = new CredentialsBean();
        String selectedItem = lista.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            RuoloNonSelezionatoException e=new RuoloNonSelezionatoException();
            GestoreEccezioni.getInstance().handleException(e);
            return;
        }
        cred.setRole(Role.valueOf(selectedItem.toUpperCase()));
        ChangePage istanza = ChangePage.getChangePage();
        try {
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/accesso2.fxml",null, null, null, cred);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void cambiaGrafica() {
        try {
            ChangePage istanza = ChangePage.getChangePage();

            Stage stagePrim = istanza.getStage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/loginPage1.fxml"));
            Scene scene = null;

            scene = new Scene(loader.load(), 1200, 760);

            stagePrim.setScene(scene);
            stagePrim.show();

        } catch (IOException e) {
            SystemException exception = new SystemException();

            exception.initCause(e);
            GestoreEccezioni.getInstance().handleException(e);
        }
    }




}
