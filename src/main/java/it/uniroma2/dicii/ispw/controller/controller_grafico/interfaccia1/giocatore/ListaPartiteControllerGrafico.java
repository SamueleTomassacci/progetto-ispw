package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;
import it.uniroma2.dicii.ispw.utils.bean.UserBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;


public class ListaPartiteControllerGrafico implements  ListaPartiteObserver {
    @FXML
    public TableColumn<PartitaBean, String> campoColumn;
    @FXML
    public TableColumn<PartitaBean, String> indirizzoColumn;
    @FXML
    public TableColumn<PartitaBean, LocalDate> dataColumn;
    @FXML
    public TableColumn<PartitaBean, LocalTime> oraColumn;
    @FXML
    public TableColumn<PartitaBean, Integer> giocatoriColumn;
    @FXML
    public TableColumn<PartitaBean, String> statoColumn;
    @FXML
    private TableView<PartitaBean> listaPartite; // lista partite correntemente visualizzate

    @Override
    public void update(PartitaBean updatedPartita) {
        // Recupera l'elenco delle partite mostrate
        ObservableList<PartitaBean> partitaBeanList = listaPartite.getItems();
        // Cerca la partita nella lista
        boolean partitaTrovata = false;
        for (PartitaBean pb : partitaBeanList) {
            if (pb.getOraInizio().equals(updatedPartita.getOraInizio()) &&
                    pb.getNomeCampo().equals(updatedPartita.getNomeCampo()) &&
                    pb.getIndirizzoCampo().equals(updatedPartita.getIndirizzoCampo()) &&
                    pb.getGiorno().equals(updatedPartita.getGiorno())) {
                // Aggiorna lo stato della partita
                pb.setStato(updatedPartita.getStato());
                // Aggiorna la TableView
                partitaTrovata = true;
                break;
            }
        }

        // Se la partita non è stata trovata, aggiungila alla lista e aggiorna la TableView
        if (!partitaTrovata) {
            partitaBeanList.add(updatedPartita);
        }

        //Aggiorna la tableView
        listaPartite.refresh();
    }

    public void inizializzaLista(CreaPartitaControllerApplicativo controllerApplicativo, UserBean user) throws SystemException {
        statoColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
        campoColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCampo"));
        indirizzoColumn.setCellValueFactory(new PropertyValueFactory<>("indirizzoCampo"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("giorno"));
        oraColumn.setCellValueFactory(new PropertyValueFactory<>("oraInizio"));
        giocatoriColumn.setCellValueFactory(new PropertyValueFactory<>("numGiocatori"));
        controllerApplicativo.inizializzaPartite(user,this);
    }

}
