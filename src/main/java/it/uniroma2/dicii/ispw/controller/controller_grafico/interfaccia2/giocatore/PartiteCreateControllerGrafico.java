package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.creapartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.GiocatoreBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.UserBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class PartiteCreateControllerGrafico extends ControllerGrafico {

    @FXML
    public Button aggiorna;
    @FXML
    public Button home;
    @FXML
    public ScrollPane scrollpane;
    private IdSessioneBean id;
    @FXML
    public Button profilo;
    private CreaPartitaControllerApplicativo controllerApplicativo;
    private ListaPartiteControllerGrafico listaPartiteControllerGrafico;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) {
        // prendiamo un istanza di controller appliativo
        controllerApplicativo = new CreaPartitaControllerApplicativo();
        // settiamo username utente
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        GiocatoreBean giocatoreBean=session.getGiocatoreBean();
        String nome=giocatoreBean.getUsername();
        profilo.setText(nome);
        // inizializziamo la lista di partite
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/giocatore/crea_partita/ListaPartiteCreate.fxml"));
            Parent content = loader.load();
            // Imposta il contenuto dello ScrollPane
            scrollpane.setContent(content);
            // Ottieni controller associato al loader
            listaPartiteControllerGrafico = loader.getController();
            //inizializza la lista
            listaPartiteControllerGrafico.inizializzaLista(controllerApplicativo, new UserBean(profilo.getText()));
        } catch (IOException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }

    }

    public void clickHome(ActionEvent actionEvent) {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/giocatore/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void clickAggiorna() {
        // Aggiorniamo la la lista delle partite visualizzate
        try {
            controllerApplicativo.aggiornaRichieste();
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
}
