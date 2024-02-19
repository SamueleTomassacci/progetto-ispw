package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;

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
import javafx.scene.control.Label;

public class HomePageControllerGrafico extends ControllerGrafico {
    private IdSessioneBean idSession;
    @FXML
    public Label messaggioBenvenuto;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) throws SystemException {
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
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/aggiungi_campo/inviaRichiesta.fxml", this.idSession, null, null,null);
        } catch (SystemException e) {
            e.printStackTrace();
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void statoPartite() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/aggiungi_campo/inviaRichiesta.fxml", this.idSession, null, null,null);
        } catch (SystemException e) {
            e.printStackTrace();
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
}
