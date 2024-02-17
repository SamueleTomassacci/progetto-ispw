package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.GiocatoreBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.ListaNomeCampoBean;
import it.uniroma2.dicii.ispw.utils.bean.NomeCampoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CreaPartita1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    @FXML
    private Label username;
    @FXML
    private DatePicker sceltaData;
    @FXML
    private ComboBox sceltaCampo;
    @FXML
    private ComboBox sceltaOrario;



    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto) throws SystemException {
        this.id=id;
        SessionManager manager = SessionManager.getSessionManager();
        Session session = manager.getSessionFromId(id);
        GiocatoreBean giocatore = session.getGiocatoreBean();
        username.setText(giocatore.getUsername());
        // inizializzazione DataPicker
        sceltaData.setValue(LocalDate.now());
        // Imposta il formato di visualizzazione del DatePicker in base al formato utilizzato in Italia
        sceltaData.setPromptText("dd-MM-yyyy");
        // Disabilita la selezione dei giorni passati
        sceltaData.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                // Disabilita i giorni passati
                setDisable(date.isBefore(today));
            }
        });
        // inizializzazione sceltaCampo
        CreaPartitaControllerApplicativo controller = new CreaPartitaControllerApplicativo();
        ListaNomeCampoBean listaNomeCampoBean = controller.inizializzasceltaCampo();
        List<NomeCampoBean> listaCampi = listaNomeCampoBean.getLista();
        for (NomeCampoBean campo : listaCampi) {
            sceltaCampo.getItems().add(campo);
        }

    }

    public void clickBack() throws SystemException, IOException {
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/homePage.fxml",this.id,null,null);
    }





}
