package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.RichiestaPartitaException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CreaPartitaControllerGrafico extends ControllerGrafico {
    @FXML
    public Spinner<LocalTime> OrarioPartita;
    @FXML
    public ComboBox<String> CampoPartita;
    @FXML
    public TextField sceltaData;

    @FXML
    public Button home;
    public Spinner<Integer> numeroGiocatori;

    private IdSessioneBean id;
    @FXML
    public Button profilo;


    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred){
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        GiocatoreBean giocatoreBean=session.getGiocatoreBean();
        String nome=giocatoreBean.getUsername();
        profilo.setText(nome);

        // Inizializzazione della scelta campo
        sceltaData.setPromptText(String.valueOf(LocalDate.now()));

        //inizializza scelta Campo
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        ListaNomeCampoBean listaNomeCampoBean = null;
        try {
            listaNomeCampoBean = controllerApplicativo.inizializzasceltaCampo();
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        List<PartitaCampoBean> listaCampi = listaNomeCampoBean.getLista();
        for (PartitaCampoBean campo : listaCampi) {
            CampoPartita.getItems().add(campo.getNome() + " - " + campo.getIndirizzo());
        }
        // Aggiunta ChangeListener
        CampoPartita.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Azione da eseguire quando l'utente seleziona un elemento nella ComboBox
            inizializzaSceltaOrario();
        });

        // Inizializza scelta Numero Giocatori
        // Inizializza numGiocatori
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 10, 4, 2);
        numeroGiocatori.setValueFactory(valueFactory);

        numeroGiocatori.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ((int) newValue % 2 != 0) {
                numeroGiocatori.getValueFactory().setValue((int) newValue + 1);
            }
        });

    }

    public void inizializzaSceltaOrario(){
        // Otteniamo la stringa selezionata dalla ComboBox
        String campoSelezionato = (String) CampoPartita.getSelectionModel().getSelectedItem();

        // Otteniamo il nome e l'indirizzo separatamente dalla stringa selezionata
        String[] partiCampo = campoSelezionato.split(" - ");
        String nomeCampo = partiCampo[0];
        String indirizzoCampo = partiCampo[1];

        // creiamo il bean PartitaCampoDataBean
        PartitaCampoDataBean richiestaorari = new PartitaCampoDataBean(nomeCampo, indirizzoCampo, LocalDate.parse(sceltaData.getText()));

        // chiamiamo la funzione inizializzasceltaOrari
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        List<LocalTime> orariPossibili = null;
        try {
            orariPossibili = controllerApplicativo.inizializzasceltaOrario(richiestaorari);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        // Inizializza lo SpinnerValueFactory
        List<LocalTime> finalOrariPossibili = orariPossibili;
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
            @Override
            public void decrement(int steps) {
                LocalTime value = getValue();
                int index = finalOrariPossibili.indexOf(value);
                if (index == -1) {
                    index = finalOrariPossibili.size() - 1;
                } else {
                    index = Math.max(0, index - steps);
                }
                setValue(finalOrariPossibili.get(index));
            }

            @Override
            public void increment(int steps) {
                LocalTime value = getValue();
                int index = finalOrariPossibili.indexOf(value);
                if (index == -1) {
                    index = 0;
                } else {
                    index = Math.min(finalOrariPossibili.size() - 1, index + steps);
                }
                setValue(finalOrariPossibili.get(index));
            }
        };
        valueFactory.setValue(orariPossibili.get(0));
        valueFactory.setConverter(new StringConverter<LocalTime>() {
            @Override
            public String toString(LocalTime object) {
                return object.toString();
            }

            @Override
            public LocalTime fromString(String string) {
                return LocalTime.parse(string);
            }
        });
        valueFactory.setWrapAround(true); // Permette la navigazione circolare degli orari
        OrarioPartita.setValueFactory(valueFactory);
    }

    public void inviaRichiesta() {
        // Prendiamo l'input inserito dall'utente
        // Otteniamo il campo
        String campoSelezionato = (String) CampoPartita.getSelectionModel().getSelectedItem();
        String[] partiCampo = campoSelezionato.split(" - ");
        String nomeCampo = partiCampo[0];
        String indirizzoCampo = partiCampo[1];
        // Otteniamo la data
        LocalDate giorno = LocalDate.parse(sceltaData.getText());
        // Otteniamo l'orario selezionato
        LocalTime orarioInizio = OrarioPartita.getValue();
        // Creiamo una RichiestaPartitaBean
        RichiestaPartitaBean richiesta = new RichiestaPartitaBean(nomeCampo, indirizzoCampo, giorno, orarioInizio, (Integer) numeroGiocatori.getValue(), profilo.getText());
        // prendiamo un istanza di controller
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        try {
            controllerApplicativo.inviaRichiesta(richiesta);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (RichiestaPartitaException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void clickHome() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/giocatore/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            e.printStackTrace();
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
}
