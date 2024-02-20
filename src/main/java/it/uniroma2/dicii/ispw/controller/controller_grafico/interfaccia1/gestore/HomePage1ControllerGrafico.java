package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.gestore;

import it.uniroma2.dicii.ispw.Main;

import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.GestoreBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;

import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HomePage1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    @FXML
    private Label user;

    @FXML
    private Button gestisci;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto, CredentialsBean cred){
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        GestoreBean gestore=session.getGestoreBean();
        String nome=gestore.getUsername();
        user.setText("Bentornato "+nome+"!");
    }

    public void clickGestisci() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/gestore/gestisci/GestisciRichieste.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
    public void logout() {
        try {
            SessionManager.getSessionManager().rimuoviSessione(id);
            ChangePage currIstance = ChangePage.getChangePage();

            Stage currStage = currIstance.getStage();
            FXMLLoader load = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/loginPage1.fxml"));
            Scene scene = null;

            scene = new Scene(load.load(), 1200, 760);

            currStage.setScene(scene);
            currStage.show();

        }catch (IOException e) {
            SystemException exception = new SystemException();

            exception.initCause(e);
            GestoreEccezioni.getInstance().handleException(e);
        }

    }



}


