package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

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
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.CampoEsistenteException;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;



public class SalvaInvia1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    private CampoSenzaFotoBean campoSenzaFotoBean;
    private FotoBean foto;
    @FXML
    private Label nome;
    @FXML
    private Label indirizzo;
    @FXML
    private Label tariffa;
    @FXML
    private Label apertura;
    @FXML
    private Label chiusura;
    @FXML
    private Label iban;
    private final String page="/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml";
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto, CredentialsBean cred){
        this.id=id;
        this.campoSenzaFotoBean=campoSenzaFotoBean;
        this.foto=foto;
        nome.setText(campoSenzaFotoBean.getNome());
        indirizzo.setText(campoSenzaFotoBean.getPosizione());
        tariffa.setText(Integer.toString(campoSenzaFotoBean.getCosto()));
        apertura.setText(campoSenzaFotoBean.getApertura().toString());
        chiusura.setText(campoSenzaFotoBean.getChiusura().toString());
        iban.setText(campoSenzaFotoBean.getPagamento());
    }

    public void backHome() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina(this.page, this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void back() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/AggiungiFoto.fxml", this.id, campoSenzaFotoBean, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void salva() {

        CampoBean richiesta = new CampoBean(campoSenzaFotoBean, foto);
        richiesta.setTentativo(1);

        SessionManager manager = SessionManager.getSessionManager();
        Session session = manager.getSessionFromId(id);
        ProprietarioBean proprietario = session.getProprietarioBean();
        AggiungiCampoControllerApplicativo controller = new AggiungiCampoControllerApplicativoBase();
        AggiungiCampoControllerApplicativoVip vip = new AggiungiCampoControllerApplicativoVip(controller);

        try {                                   //Tento di inserire la richiesta

            if (proprietario.getVip() == 1) {

                vip.inviaRichiestaGestore(richiesta, proprietario);

            } else {

                controller.inviaRichiestaGestore(richiesta, proprietario);
            }
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina(this.page, this.id, null, null,null);

        } catch (SystemException exc) {
            GestoreEccezioni.getInstance().handleException(exc);

        } catch (CampoEsistenteException e) {        //Nel caso in cui il campo inserito è già esistente gestisco l'eccezione andando a chiedere al proprietario, se il centro sportivo possiede più campi, in tal caso lo salvo

            richiesta.setTentativo(2);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attenzione");
            alert.setHeaderText("Il campo inserito è già presente nel nostro sistema.\n Se il suo centro sportivo ospita più di un campo da basket e vuole procedere con il salvatggio di questo nuovo campo, clicchi conferma.");


            ButtonType buttonTypeOne = new ButtonType("Conferma");
            ButtonType buttonTypeCancel = new ButtonType("Annulla");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            alert.showAndWait().ifPresent(buttonType -> {

                try {


                    if (buttonType == buttonTypeOne) {

                        riprovaSalvataggio(richiesta, e, proprietario);

                    }


                    ChangePage istanza = ChangePage.getChangePage();
                    istanza.cambiaPagina(this.page, this.id, null, null,null);


                } catch (SystemException | CampoEsistenteException exce) {

                    GestoreEccezioni.getInstance().handleException(exce);
                }
            });

        }
    }

    public void riprovaSalvataggio(CampoBean richiesta, CampoEsistenteException e,ProprietarioBean proprietario) throws SystemException, CampoEsistenteException{

        AggiungiCampoControllerApplicativo controller = new AggiungiCampoControllerApplicativoBase();
        AggiungiCampoControllerApplicativoVip vip = new AggiungiCampoControllerApplicativoVip(controller);
        AggiungiCampoControllerApplicativoBase contr = new AggiungiCampoControllerApplicativoBase();

        int num=0;

        if(e.getMessage().equals("Messaggio standard")) {               //C'è già un campo con lo stesso indirizzo salvato

            num = contr.getNumeroMax(richiesta);
            num++;

        }

        else {                                                         //C'è un campo con lo stesso indirizzo nelle richieste
            num=Integer.parseInt(e.getMessage());
            num++;


        }
        //Modifico il nome del campo in base al numero
        String nomeCampo = richiesta.getNomeCampo();
        if (Character.isDigit(nomeCampo.charAt(nomeCampo.length() - 1))) {
            richiesta.setNomeCampo(nomeCampo.substring(0, nomeCampo.length() - 1) + Integer.toString(num));

        } else {
            richiesta.setNomeCampo(richiesta.getNomeCampo() + Integer.toString(num));
        }

        richiesta.setNum(num);
        System.out.println(richiesta.getNomeCampo());


        if (proprietario.getVip() == 1) {
            vip.inviaRichiestaGestore(richiesta, proprietario);

        } else {
            controller.inviaRichiestaGestore(richiesta, proprietario);
        }


    }

}


