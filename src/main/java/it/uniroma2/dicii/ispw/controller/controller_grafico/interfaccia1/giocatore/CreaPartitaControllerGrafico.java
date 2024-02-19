package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CreaPartitaControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    private CreaPartitaControllerApplicativo controllerApplicativo; // riferimento all'istanza utilizzata del controller applicativo
    private TabellaPartiteControllerGrafico tabellaPartiteControllerGrafico; // riferimento all'istanza del controller grafico caricato
    @FXML
    public Spinner numGiocatori;
    @FXML
    private Label username;
    @FXML
    private DatePicker sceltaData;
    @FXML
    private ComboBox sceltaCampo;
    @FXML
    private ComboBox sceltaOrario;
    @FXML
    private ScrollPane scrollPane;



    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) throws SystemException {
        // creo un istanza di controller applicativo
        controllerApplicativo = new CreaPartitaControllerApplicativo();

        // imposto nome utente nella pagina
        this.id = id;
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
        ListaNomeCampoBean listaNomeCampoBean = controllerApplicativo.inizializzasceltaCampo();
        List<PartitaCampoBean> listaCampi = listaNomeCampoBean.getLista();
        for (PartitaCampoBean campo : listaCampi) {
            sceltaCampo.getItems().add(campo.getNome() + " - " + campo.getIndirizzo());
        }
        // Aggiunta ChangeListener
        sceltaCampo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Azione da eseguire quando l'utente seleziona un elemento nella ComboBox
            try {
                inizializzaSceltaOrario();
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        });

        // Inizializza numGiocatori
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 10, 4, 2);
        numGiocatori.setValueFactory(valueFactory);

        numGiocatori.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ((int) newValue % 2 != 0) {
                numGiocatori.getValueFactory().setValue((int) newValue + 1);
            }
        });
        // Inizializza Finestra Partite Create
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/crea_partita/TabellaStatoPartite.fxml"));
            Parent content = loader.load();
            // Imposta il contenuto dello ScrollPane
            scrollPane.setContent(content);
            // Ottieni controller associato al loader
            tabellaPartiteControllerGrafico = loader.getController();
            //inizializza la lista
            tabellaPartiteControllerGrafico.inizializzaLista(controllerApplicativo, new UserBean(username.getText()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickBack() throws SystemException, IOException {
        ChangePage istanza = ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/homePage.fxml",this.id,null,null,null);
    }

    public void inizializzaSceltaOrario() throws SystemException {
        // Otteniamo la stringa selezionata dalla ComboBox
        String campoSelezionato = (String) sceltaCampo.getSelectionModel().getSelectedItem();

        // Otteniamo il nome e l'indirizzo separatamente dalla stringa selezionata
        String[] partiCampo = campoSelezionato.split(" - ");
        String nomeCampo = partiCampo[0];
        String indirizzoCampo = partiCampo[1];

        // creiamo il bean PartitaCampoDataBean
        PartitaCampoDataBean richiestaorari = new PartitaCampoDataBean(nomeCampo, indirizzoCampo, sceltaData.getValue());

        // chiamiamo la funzione inizializzasceltaOrari
        List<LocalTime> orariPossibili = controllerApplicativo.inizializzasceltaOrario(richiestaorari);
        for (LocalTime orario : orariPossibili) {
            sceltaOrario.getItems().add(orario);
        }
    }

    public void clickRichiesta() throws SystemException {
        // Prendiamo l'input inserito dall'utente
        // Otteniamo il campo
        String campoSelezionato = (String) sceltaCampo.getSelectionModel().getSelectedItem();
        String[] partiCampo = campoSelezionato.split(" - ");
        String nomeCampo = partiCampo[0];
        String indirizzoCampo = partiCampo[1];
        // Otteniamo la data
        LocalDate giorno = sceltaData.getValue();
        // Otteniamo l'orario selezionato
        LocalTime orarioInizio = (LocalTime) sceltaOrario.getSelectionModel().getSelectedItem();

        // Creiamo una RichiestaPartitaBean
        RichiestaPartitaBean richiesta = new RichiestaPartitaBean(nomeCampo, indirizzoCampo, giorno, orarioInizio, (Integer) numGiocatori.getValue(), username.getText());
        // prendiamo un istanza di controller
        controllerApplicativo.inviaRichiesta(richiesta);
    }

    public void clickAggiorna() throws SystemException {
        // Aggiorniamo la la lista delle partite visualizzate
        controllerApplicativo.aggiornaRichieste();
    }

}
