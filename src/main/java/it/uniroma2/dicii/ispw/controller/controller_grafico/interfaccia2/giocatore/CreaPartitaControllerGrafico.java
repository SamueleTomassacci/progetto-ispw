package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;

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
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CreaPartitaControllerGrafico extends ControllerGrafico {
    @FXML
    private Spinner<LocalTime> orarioPartita;
    @FXML
    private ComboBox<String> campoPartita;
    @FXML
    private TextField sceltaData;

    @FXML
    private Button home;
    @FXML
    private Spinner<Integer> numeroGiocatori;

    private IdSessioneBean id;
    @FXML
    private Button profilo;


    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred){
        try {
            this.id = id;
            SessionManager manager = SessionManager.getSessionManager();
            Session session = manager.getSessionFromId(id);
            GiocatoreBean giocatoreBean = session.getGiocatoreBean();
            String nome = giocatoreBean.getUsername();
            profilo.setText(nome);

            // Inizializzazione della scelta campo
            sceltaData.setPromptText(String.valueOf(LocalDate.now()));

            //inizializza scelta Campo
            CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
            ListaNomeCampoBean listaNomeCampoBean = null;

            listaNomeCampoBean = controllerApplicativo.inizializzasceltaCampo();
            List<PartitaCampoBean> listaCampi = listaNomeCampoBean.getLista();
            for (PartitaCampoBean campo : listaCampi) {
                campoPartita.getItems().add(campo.getNome() + " - " + campo.getIndirizzo());
            }
            // Aggiunta ChangeListener
            campoPartita.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                // Azione da eseguire quando l'utente seleziona un elemento nella ComboBox
                inizializzaSceltaOrario();
            });

            // Inizializza scelta Numero Giocatori
            // Inizializza numGiocatori
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 10, 4, 2);
            numeroGiocatori.setValueFactory(valueFactory);

            numeroGiocatori.valueProperty().addListener((observable, oldValue, newValue) -> {
                if ( newValue % 2 != 0) {
                    numeroGiocatori.getValueFactory().setValue( newValue + 1);
                }
            });
        } catch (SystemException e){
            GestoreEccezioni.getInstance().handleException(e);
        }

    }

    public void inizializzaSceltaOrario(){
        try {
            // Otteniamo la stringa selezionata dalla ComboBox
            String campoSelezionato = campoPartita.getSelectionModel().getSelectedItem();
            if(campoSelezionato == null){
                throw new CampoMancanteException();
            }

            // Otteniamo il nome e l'indirizzo separatamente dalla stringa selezionata
            String[] partiCampo = campoSelezionato.split(" - ");
            String nomeCampo = partiCampo[0];
            String indirizzoCampo = partiCampo[1];

            // Otteniamo la data inserita
            LocalDate giorno = LocalDate.parse(sceltaData.getText());
            if(giorno == null){
                throw new DataMancanteException();
            }

            // creiamo il bean PartitaCampoDataBean
            PartitaCampoDataBean richiestaorari = new PartitaCampoDataBean(nomeCampo, indirizzoCampo, giorno);

            // chiamiamo la funzione inizializzasceltaOrari
            CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
            List<LocalTime> orariPossibili = null;
            orariPossibili = controllerApplicativo.inizializzasceltaOrario(richiestaorari);
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
            orarioPartita.setValueFactory(valueFactory);
        } catch (SystemException | CampoMancanteException e) {
            GestoreEccezioni.getInstance().handleException(e);
        } catch (DataMancanteException e) {
            sceltaData.setText(String.valueOf(LocalDate.now()));
            GestoreEccezioni.getInstance().handleException(e);
            inizializzaSceltaOrario();
        } catch (DateTimeParseException e){
            sceltaData.setText(String.valueOf(LocalDate.now()));
            DataFormatoErratoException f = new DataFormatoErratoException();
            GestoreEccezioni.getInstance().handleException(f);
            inizializzaSceltaOrario();
        }
    }

    public void inviaRichiesta() {
        try{
            // Prendiamo l'input inserito dall'utente
            // Otteniamo il campo
            String campoSelezionato = campoPartita.getSelectionModel().getSelectedItem();
            if(campoSelezionato == null){
                throw new CampoMancanteException();
            }
            String[] partiCampo = campoSelezionato.split(" - ");
            String nomeCampo = partiCampo[0];
            String indirizzoCampo = partiCampo[1];
            // Otteniamo la data
            LocalDate giorno = LocalDate.parse(sceltaData.getText());
            // Otteniamo l'orario selezionato
            LocalTime orarioInizio = orarioPartita.getValue();
            // Creiamo una RichiestaPartitaBean
            RichiestaPartitaBean richiesta = new RichiestaPartitaBean(nomeCampo, indirizzoCampo, giorno, orarioInizio, numeroGiocatori.getValue(), profilo.getText());
            // prendiamo un istanza di controller
            CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
            controllerApplicativo.inviaRichiesta(richiesta);
            // mostriamo un box di successo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo");
            alert.setHeaderText(null);
            alert.setContentText("La richiesta di partita è stata inviata con successo!");
            alert.showAndWait();
        } catch (RichiestaPartitaException | SystemException | CampoMancanteException e) {
            GestoreEccezioni.getInstance().handleException(e);
        } catch (DateTimeParseException e){
            sceltaData.setText(String.valueOf(LocalDate.now()));
            DataFormatoErratoException f = new DataFormatoErratoException();
            GestoreEccezioni.getInstance().handleException(f);
            inizializzaSceltaOrario();
        }
    }

    public void clickHome() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/giocatore/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {

            GestoreEccezioni.getInstance().handleException(e);
        }
    }
}
