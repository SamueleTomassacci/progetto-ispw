package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.creapartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CreaPartitaControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    private CreaPartitaControllerApplicativo controllerApplicativo; // riferimento all'istanza utilizzata del controller applicativo
    @FXML
    public Spinner<Integer> numGiocatori;
    @FXML
    private Label username;
    @FXML
    private DatePicker sceltaData;
    @FXML
    private ComboBox<String> sceltaCampo;
    @FXML
    private ComboBox<LocalTime> sceltaOrario;
    @FXML
    private ScrollPane scrollPane;



    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) {
        try {
        // creo un istanza di controller applicativo
        controllerApplicativo = new CreaPartitaControllerApplicativo();

        // imposto nome utente nella pagina
        this.id = id;
        SessionManager manager = SessionManager.getSessionManager();
        Session session = manager.getSessionFromId(id);
        GiocatoreBean giocatore = session.getGiocatoreBean();
        username.setText(giocatore.getUsername());

        // inizializzazione DataPicker
        sceltaData.setPromptText(String.valueOf(LocalDate.now()));
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
        sceltaData.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Esegui un'azione quando la data viene selezionata
            if( sceltaCampo.getSelectionModel().getSelectedItem() != null){
                inizializzaSceltaOrario();
                sceltaOrario.getSelectionModel().clearSelection();
            }
        });

        // inizializzazione sceltaCampo
        ListaNomeCampoBean listaNomeCampoBean = controllerApplicativo.inizializzasceltaCampo();
        List<PartitaCampoBean> listaCampi = listaNomeCampoBean.getLista();
        for (PartitaCampoBean campo : listaCampi) {
            sceltaCampo.getItems().add(campo.getNome() + " - " + campo.getIndirizzo());
        }
        // Aggiunta ChangeListenel
        sceltaCampo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Azione da eseguire quando l'utente seleziona un elemento nella ComboBox
            inizializzaSceltaOrario();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/crea_partita/TabellaStatoPartite.fxml"));
            Parent content = loader.load();
            // Imposta il contenuto dello ScrollPane
            scrollPane.setContent(content);
            // Ottieni controller associato al loader
            // riferimento all'istanza del controller grafico caricato
            TabellaPartiteControllerGrafico tabellaPartiteControllerGrafico = loader.getController();
            //inizializza la lista
            tabellaPartiteControllerGrafico.inizializzaLista(controllerApplicativo, new UserBean(username.getText()));
        } catch (IOException | SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }catch (DateTimeParseException e){
            DataFormatoErratoException f = new DataFormatoErratoException();
            GestoreEccezioni.getInstance().handleException(f);
        }
    }

    public void clickBack() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/homePage.fxml",this.id,null,null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void inizializzaSceltaOrario() {
        try {
            sceltaOrario.getItems().clear();
            // Otteniamo la stringa selezionata dalla ComboBox
            String campoSelezionato = (String) sceltaCampo.getSelectionModel().getSelectedItem();

            // Otteniamo il nome e l'indirizzo separatamente dalla stringa selezionata
            String[] partiCampo = campoSelezionato.split(" - ");
            String nomeCampo = partiCampo[0];
            String indirizzoCampo = partiCampo[1];

            // prendiamo la data selezionata
            LocalDate giorno = sceltaData.getValue();
            if(giorno == null){
                throw new DataMancanteException();
            }

            // creiamo il bean PartitaCampoDataBean
            PartitaCampoDataBean richiestaorari = new PartitaCampoDataBean(nomeCampo, indirizzoCampo, sceltaData.getValue());

            // chiamiamo la funzione inizializzasceltaOrari
            List<LocalTime> orariPossibili = null;

                orariPossibili = controllerApplicativo.inizializzasceltaOrario(richiestaorari);

            for (LocalTime orario : orariPossibili) {
                if(giorno.isEqual(LocalDate.now()) && LocalTime.now().isAfter(orario)){
                    continue;
                }
                sceltaOrario.getItems().add(orario);
            }
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        } catch (DataMancanteException e){
            sceltaData.setValue(LocalDate.now());
            GestoreEccezioni.getInstance().handleException(e);
            inizializzaSceltaOrario();
        } catch (DateTimeParseException e){
            sceltaData.setValue(LocalDate.now());
            DataFormatoErratoException f = new DataFormatoErratoException();
            GestoreEccezioni.getInstance().handleException(f);
            inizializzaSceltaOrario();
        }
    }

    public void clickRichiesta() {
        try {
            // Prendiamo l'input inserito dall'utente
            // Otteniamo il campo
            String campoSelezionato = (String) sceltaCampo.getSelectionModel().getSelectedItem();
            if(campoSelezionato == null){
                throw new CampoMancanteException();
            }
            String[] partiCampo = campoSelezionato.split(" - ");
            String nomeCampo = partiCampo[0];
            String indirizzoCampo = partiCampo[1];
            // Otteniamo la data
            LocalDate giorno = LocalDate.parse(sceltaData.getValue().toString());
            if(giorno == null){
                throw new DataMancanteException();
            }
            // Otteniamo l'orario selezionato
            LocalTime orarioInizio = (LocalTime) sceltaOrario.getSelectionModel().getSelectedItem();
            if(orarioInizio == null){
                throw new OrarioNonSelezionatoExcption();
            }

            // Creiamo una RichiestaPartitaBean
            RichiestaPartitaBean richiesta = new RichiestaPartitaBean(nomeCampo, indirizzoCampo, giorno, orarioInizio, (Integer) numGiocatori.getValue(), username.getText());
            // prendiamo un istanza di controller
            controllerApplicativo.inviaRichiesta(richiesta);
            // mostriamo un box di successo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo");
            alert.setHeaderText(null);
            alert.setContentText("La richiesta di partita è stata inviata con successo!");
            alert.showAndWait();
        } catch (SystemException | CampoMancanteException | DataMancanteException | RichiestaPartitaException |
                 OrarioNonSelezionatoExcption e) {
            GestoreEccezioni.getInstance().handleException(e);
        } catch (DateTimeParseException e){
            sceltaData.setValue(LocalDate.now());
            DataFormatoErratoException f = new DataFormatoErratoException();
            GestoreEccezioni.getInstance().handleException(f);
            inizializzaSceltaOrario();
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
