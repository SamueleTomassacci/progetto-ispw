package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.model.partita.statoPartita;
import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class RigaRichiestaPartitaControllerGrafico {

    @FXML
    public Text creatore;
    @FXML
    public Text nomeCampo;
    @FXML
    public Text indirizzo;
    @FXML
    public Text data;
    @FXML
    private Pane pane;
    @FXML
    public Text ora;

    private PartitaBean bean;
    private GestionalePartiteControllerGrafico father;



    public void clickAccetta() throws SystemException {
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        bean.setStato(statoPartita.Accettata);
        controllerApplicativo.rispondiRichiesta(bean);
        father.rimuoviRiga(pane);
    }
    public void clickRifiuta() throws SystemException {
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        bean.setStato(statoPartita.Rifiutata);
        controllerApplicativo.rispondiRichiesta(bean);
        father.rimuoviRiga(pane);
    }

    public void inizializza(GestionalePartiteControllerGrafico gestionalePartiteControllerGrafico, PartitaBean richiestaPartita) throws SystemException {
        bean = richiestaPartita;
        father = gestionalePartiteControllerGrafico;
        creatore.setText(richiestaPartita.getCreatore());
        nomeCampo.setText(richiestaPartita.getNomeCampo());
        indirizzo.setText(richiestaPartita.getIndirizzoCampo());
        data.setText(String.valueOf(richiestaPartita.getGiorno()));
        ora.setText(String.valueOf(richiestaPartita.getOraInizio()));
    }
}
