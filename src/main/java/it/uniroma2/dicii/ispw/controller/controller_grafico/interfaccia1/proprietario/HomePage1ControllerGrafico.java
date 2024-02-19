package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.VipControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import it.uniroma2.dicii.ispw.utils.exceptions.VipException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;


public class HomePage1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    @FXML
    private Label user;
    @FXML
    private Button aggiungi;
    @FXML
    private Button gestisci;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto, PartitaBean richiestaPartita){
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietario=session.getProprietarioBean();
        String nome=proprietario.getUsername();
        user.setText("Bentornato "+nome+"!");
    }

    public void clickAggiungi() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/compilaScheda.fxml", this.id, null, null);
        } catch (SystemException | IOException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void clickGestisciPartite() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/crea_partita/richiestePartite.fxml", this.id, null, null);
        } catch (SystemException | IOException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }



    public void upgradeVip() {
       try {
           SessionManager manager = SessionManager.getSessionManager();
           Session session = manager.getSessionFromId(id);
           ProprietarioBean proprietario = session.getProprietarioBean();

           if (proprietario.getVip() == 1) {
               throw new VipException();
           }

           else {
               VipControllerApplicativo controller = new VipControllerApplicativo();
               controller.upgradeVip(proprietario);
               int vip = 1;
               proprietario.setVip(vip);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Upgrade");
               alert.setHeaderText(null);
               alert.setContentText("Upgrade a vip eseguito!");
               alert.showAndWait();
           }
       }catch(VipException | SystemException e){
           GestoreEccezioni.getInstance().handleException(e);
       }


    }


}
