package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.GiocatoreBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageControllerGrafico extends ControllerGrafico {
    private IdSessioneBean idSession;
    @FXML
    public Label messaggioBenvenuto;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) {
        this.idSession=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        GiocatoreBean giocatoreBean=session.getGiocatoreBean();
        String nome=giocatoreBean.getUsername();
        messaggioBenvenuto.setText("Bentornato "+nome+"!");
    }

    public void creaPartita() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/giocatore/crea_partita/creaPartita.fxml", this.idSession, null, null,null);
        } catch (SystemException e) {

            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void statoPartite() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/giocatore/crea_partita/statoPartiteCreate.fxml", this.idSession, null, null,null);
        } catch (SystemException e) {

            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void logout() {
        try {
            SessionManager.getSessionManager().rimuoviSessione(idSession);
            ChangePage ist = ChangePage.getChangePage();

            Stage stagePrim = ist.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/loginPage2.fxml"));
            Scene scene = null;

            scene = new Scene(fxmlLoader.load(), 1200, 760);

            stagePrim.setScene(scene);
            stagePrim.show();

        }catch (IOException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            GestoreEccezioni.getInstance().handleException(e);
        }

    }

}
