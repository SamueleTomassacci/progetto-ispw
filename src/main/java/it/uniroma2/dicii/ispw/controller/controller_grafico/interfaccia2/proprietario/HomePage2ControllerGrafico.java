package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.VipControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import it.uniroma2.dicii.ispw.utils.exceptions.VipException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage2ControllerGrafico extends ControllerGrafico {
    @FXML
    private Label titolo;
    private IdSessioneBean idSession;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred){
        this.idSession=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietarioBean=session.getProprietarioBean();
        String nome=proprietarioBean.getUsername();
        titolo.setText("Bentornato "+nome+"!");
    }
    public void aggiungiCampo(){
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/aggiungi_campo/inviaRichiesta.fxml", this.idSession, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }

    }
    public void gestisci(){
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/gestisci_prenotazioni/GestionaleRichieste.fxml", this.idSession, null, null,null);
        } catch (SystemException e) {

            GestoreEccezioni.getInstance().handleException(e);
        }
    }
    public void diventaVip() {
        try {
            SessionManager manager = SessionManager.getSessionManager();
            Session session = manager.getSessionFromId(idSession);
            ProprietarioBean proprietarioBean = session.getProprietarioBean();

            if (proprietarioBean.getVip() == 1) {
                throw new VipException();
            } else {
                VipControllerApplicativo controller = new VipControllerApplicativo();
                controller.upgradeVip(proprietarioBean);
                int vip = 1;
                proprietarioBean.setVip(vip);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upgrade");
                alert.setHeaderText(null);
                alert.setContentText("Upgrade a vip eseguito!");
                alert.showAndWait();
            }
        } catch (VipException | SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }


    public void logout() {
        try {
            SessionManager.getSessionManager().rimuoviSessione(idSession);
            ChangePage changePage = ChangePage.getChangePage();

            Stage stagePrim = changePage.getStage();
            FXMLLoader loaderFxml = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/loginPage2.fxml"));
            Scene scene = null;

            scene = new Scene(loaderFxml.load(), 1200, 760);

            stagePrim.setScene(scene);
            stagePrim.show();

        }catch (IOException e) {
            SystemException exception = new SystemException();

            exception.initCause(e);
            GestoreEccezioni.getInstance().handleException(e);
        }

    }
}
