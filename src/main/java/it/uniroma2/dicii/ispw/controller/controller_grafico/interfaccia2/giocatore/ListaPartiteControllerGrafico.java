package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore.ListaPartiteObserver;
import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;
import it.uniroma2.dicii.ispw.utils.bean.UserBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ListaPartiteControllerGrafico implements ListaPartiteObserver {

    @FXML
    private ListView<PartitaBean> listView;

    @FXML
    public void initialize() {
        listView.setCellFactory(param -> new ListCell<PartitaBean>() {
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
    }

    @Override
    public void update(PartitaBean partita) {
        // Recupera l'elenco delle partite mostrate
        ObservableList<PartitaBean> partitaBeanList = listView.getItems();
        // Controlla se la partita esiste già nella lista
        boolean partitaEsistente = false;
        int index = 0;
        for (int i = 0; i < partitaBeanList.size(); i++) {
            PartitaBean p = partitaBeanList.get(i);
            if (p.getNomeCampo().equals(partita.getNomeCampo()) &&
                    p.getIndirizzoCampo().equals(partita.getIndirizzoCampo()) &&
                    p.getGiorno().equals(partita.getGiorno()) &&
                    p.getOraInizio().equals(partita.getOraInizio())) {
                partitaEsistente = true;
                index = i;
                break;
            }
        }
        // Altrimenti, aggiungi una nuova riga alla ListView
        if (partitaEsistente) {
            partitaBeanList.set(index, partita); // Aggiorna il PartitaBean esistente nella lista
        } else {
            partitaBeanList.add(partita); // Aggiungi un nuovo PartitaBean alla lista
        }
    }

    public void inizializzaLista(CreaPartitaControllerApplicativo controllerApplicativo, UserBean userBean){
        try {
            controllerApplicativo.inizializzaPartite(userBean,this);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }
}
