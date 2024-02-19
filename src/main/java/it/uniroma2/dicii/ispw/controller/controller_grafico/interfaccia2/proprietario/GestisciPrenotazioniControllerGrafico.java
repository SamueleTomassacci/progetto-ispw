package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario.RigaRichiestaPartitaControllerGrafico;
import it.uniroma2.dicii.ispw.model.partita.statoPartita;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.List;

public class GestisciPrenotazioniControllerGrafico extends ControllerGrafico {

    private IdSessioneBean id;
    @FXML
    private Button profilo;
    @FXML
    private ListView<PartitaBean> listview;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) throws SystemException {
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietarioBean=session.getProprietarioBean();
        String nome=proprietarioBean.getUsername();
        profilo.setText(nome);

        //inizializza la lista
        listview.setCellFactory(param -> new ListCell<PartitaBean>() {
            @Override
            protected void updateItem(PartitaBean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNomeCampo() + " - " + item.getIndirizzoCampo() + " - " + item.getGiorno() + " - " + item.getOraInizio() + " - " + item.getStato());
                }
            }
        });
        inizializzalista();
    }

    public void clickRifiuta(ActionEvent actionEvent) {
        PartitaBean partitaSelezionata = listview.getSelectionModel().getSelectedItem();
        partitaSelezionata.setStato(statoPartita.Rifiutata);
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        try {
            controllerApplicativo.rispondiRichiesta(partitaSelezionata);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        listview.getItems().remove(partitaSelezionata);
    }

    public void clickAccetta(ActionEvent actionEvent) {
        PartitaBean partitaSelezionata = listview.getSelectionModel().getSelectedItem();
        partitaSelezionata.setStato(statoPartita.Accettata);
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        try {
            controllerApplicativo.rispondiRichiesta(partitaSelezionata);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        listview.getItems().remove(partitaSelezionata);
    }

    public void back(ActionEvent actionEvent) {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void inizializzalista(){
        // creiamo il bean per indicare il proprietario
        UserBean proprietario = new UserBean(profilo.getText());
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        List<PartitaBean> lista = null;
        try {
            lista = controllerApplicativo.inizializzaRichiesteProprietario(proprietario);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        for (PartitaBean partita : lista){
            // Controlla se partita è all'interno della listview
            boolean alreadyInList = false;
            for (PartitaBean item : listview.getItems()) {
                if (partita.equals(item)) {
                    alreadyInList = true;
                    break;
                }
            }

            // Aggiungi la partita se non è presente nella listview
            if (!alreadyInList) {
                listview.getItems().add(partita);
            }
        }
    }
}