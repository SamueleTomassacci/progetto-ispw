package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.GiocatoreBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomePage1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    @FXML
    private Label user;
    @FXML
    private Button Creazione;
    @FXML
    private Button Partecipazione;
    @FXML
    private Label altezza;
    @FXML
    private Label eta;
    @FXML
    private Label ruoloGiocatore;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto) throws SystemException {
        this.id=id;
        SessionManager manager = SessionManager.getSessionManager();
        Session session = manager.getSessionFromId(id);
        GiocatoreBean giocatore = session.getGiocatoreBean();
        user.setText("Bentornato "+giocatore.getUsername()+"!");
        altezza.setText(String.valueOf(giocatore.getAltezza()));
        eta.setText(String.valueOf(giocatore.getEta()));
        ruoloGiocatore.setText(giocatore.getRuoloBasket());
    }
    public void clickCreazione() throws IOException , SystemException{
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/crea_partita/CreaPartita1.fxml",this.id,null,null);
    }

}
