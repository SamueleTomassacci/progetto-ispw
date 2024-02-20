package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario.aggiungi_campo;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoVip;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Time;

public class AggiungiCampo2ControllerGrafico extends ControllerGrafico {
    @FXML
    private Button profilo;
    @FXML
    private TextField nome;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField iban;
    @FXML
    private TextField tariffa;
    @FXML
    private ComboBox<String> apertura;
    @FXML
    private ComboBox<String> chiusura;
    @FXML
    private ImageView immagine;
    private File img;
    private final static String page="/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/homePage.fxml";


    private IdSessioneBean id;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred){
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietarioBean=session.getProprietarioBean();
        String nomeProp=proprietarioBean.getUsername();
        profilo.setText(nomeProp);
        apertura.getItems().addAll("07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00");
        chiusura.getItems().addAll("08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00");
    }

    public void scegliFoto(){



        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleziona un'immagine");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Immagine", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        ChangePage istanza=ChangePage.getChangePage();
        Stage stage=istanza.getStage();
        File selectedFile = chooser.showOpenDialog(stage).getAbsoluteFile();
        this.img=selectedFile;

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            this.immagine.setImage(image);
        }

    }

    public void invia() {
        CampoBean campo = new CampoBean();
        campo.setNomeCampo(nome.getText());
        campo.setIndirizzo(indirizzo.getText());
        campo.setIban(iban.getText());


        try {
            campo.setTariffa(Integer.valueOf(tariffa.getText()));
            campo.setOrarioApertura(Time.valueOf(apertura.getValue()+":00"));
            campo.setOrarioChiusura(Time.valueOf(chiusura.getValue()+":00"));
            if (this.img == null) {
                throw new FotoMancanteException();
            }
            campo.setImmagine(this.img);
        }catch(NumberFormatException tar){
            TariffaException tariffaException=new TariffaException();
            GestoreEccezioni.getInstance().handleException(tariffaException);
            return;
        } catch (IllegalArgumentException e) {
            OrarioNonSelezionatoExcption exce = new OrarioNonSelezionatoExcption();
            GestoreEccezioni.getInstance().handleException(exce);
            return;
        } catch (FotoMancanteException exc) {
            GestoreEccezioni.getInstance().handleException(exc);
            return;
        }

        campo.setTentativo(1);

        SessionManager manager = SessionManager.getSessionManager();
        Session session = manager.getSessionFromId(id);
        ProprietarioBean proprietario = session.getProprietarioBean();
        AggiungiCampoControllerApplicativo controller = new AggiungiCampoControllerApplicativoBase();
        AggiungiCampoControllerApplicativoVip vip = new AggiungiCampoControllerApplicativoVip(controller);

        try {                                   //Tento di inserire la richiesta

            if (proprietario.getVip() == 1) {

                vip.inviaRichiestaGestore(campo, proprietario);

            } else {

                controller.inviaRichiestaGestore(campo, proprietario);
            }
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina(this.page, this.id, null, null,null);

        } catch (SystemException exc) {
            GestoreEccezioni.getInstance().handleException(exc);

        } catch (CampoEsistenteException e) {        //Nel caso in cui il campo inserito è già esistente gestisco l'eccezione andando a chiedere al proprietario, se il centro sportivo possiede più campi, in tal caso lo salvo

            campo.setTentativo(2);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attenzione");
            alert.setHeaderText("Il campo inserito è già presente nel nostro sistema.\n Se il suo centro sportivo ospita più di un campo da basket e vuole procedere con il salvatggio di questo nuovo campo, clicchi conferma.");


            ButtonType buttonTypeOne = new ButtonType("Conferma");
            ButtonType buttonTypeCancel = new ButtonType("Annulla");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            alert.showAndWait().ifPresent(buttonType -> {

                try {


                    if (buttonType == buttonTypeOne) {

                        riprovaSalvataggio(campo, e, proprietario);

                    }


                    ChangePage istanza = ChangePage.getChangePage();
                    istanza.cambiaPagina(this.page, this.id, null, null,null);


                } catch (SystemException | CampoEsistenteException exce) {

                    GestoreEccezioni.getInstance().handleException(exce);
                }
            });

        }


    }

    private void riprovaSalvataggio(CampoBean richiestaCampo, CampoEsistenteException exc,ProprietarioBean proprietarioCampo) throws SystemException, CampoEsistenteException{

        AggiungiCampoControllerApplicativo controllerAppl = new AggiungiCampoControllerApplicativoBase();
        AggiungiCampoControllerApplicativoVip vip = new AggiungiCampoControllerApplicativoVip(controllerAppl);
        AggiungiCampoControllerApplicativoBase contr = new AggiungiCampoControllerApplicativoBase();

        int num=0;

        if(exc.getMessage().equals("Messaggio standard")) {               //C'è già un campo con lo stesso indirizzo salvato

            num = contr.getNumeroMax(richiestaCampo);
            num++;

        }

        else {                                                         //C'è un campo con lo stesso indirizzo nelle richieste
            num=Integer.parseInt(exc.getMessage());
            num++;


        }
        //Modifico il nome del campo in base al numero
        String nomeCampo = richiestaCampo.getNomeCampo();
        if (Character.isDigit(nomeCampo.charAt(nomeCampo.length() - 1))) {
            richiestaCampo.setNomeCampo(nomeCampo.substring(0, nomeCampo.length() - 1) + Integer.toString(num));

        } else {
            richiestaCampo.setNomeCampo(richiestaCampo.getNomeCampo() + Integer.toString(num));
        }

        richiestaCampo.setNum(num);



        if (proprietarioCampo.getVip() == 1) {
            vip.inviaRichiestaGestore(richiestaCampo, proprietarioCampo);

        } else {
            controllerAppl.inviaRichiestaGestore(richiestaCampo, proprietarioCampo);
        }


    }

    public void back(){
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
}
