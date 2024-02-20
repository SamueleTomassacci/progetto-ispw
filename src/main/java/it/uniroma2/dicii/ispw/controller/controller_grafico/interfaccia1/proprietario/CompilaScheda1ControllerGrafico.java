package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;

import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.OrarioException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import it.uniroma2.dicii.ispw.utils.exceptions.TariffaException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



import java.sql.Time;



public class CompilaScheda1ControllerGrafico extends ControllerGrafico {
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField nomeCampo;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField tariffa;
    @FXML
    private TextField apertura;
    @FXML
    private TextField chiusura;
    @FXML
    private TextField iban;
    private IdSessioneBean id;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto, CredentialsBean cred){
        this.id=id;
    }

    public void backHome() {
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void clickAvanti() {

        try{

        CampoSenzaFotoBean richiesta=new CampoSenzaFotoBean(nomeCampo.getText(),indirizzo.getText(), Integer.valueOf(tariffa.getText()), Time.valueOf(apertura.getText()),Time.valueOf(chiusura.getText()),iban.getText());
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/AggiungiFoto.fxml", this.id,richiesta,null,null);

        }catch(NumberFormatException e){
            TariffaException tariffaException=new TariffaException();
            GestoreEccezioni.getInstance().handleException(tariffaException);

        }catch(SystemException e){
            GestoreEccezioni.getInstance().handleException(e);

        }catch(IllegalArgumentException e){          //Gestione dell'eccezione del formato del tempo, se si mettono soltanto minuti e ora senza secondi vengono aggiunti
            try{
                String strApertura=apertura.getText()+":00";
                String strChiusura=chiusura.getText()+":00";
                Time oraApertura= Time.valueOf(strApertura);
                Time oraChiusura=Time.valueOf(strChiusura);
                CampoSenzaFotoBean richiesta=new CampoSenzaFotoBean(nomeCampo.getText(),indirizzo.getText(), Integer.valueOf(tariffa.getText()), oraApertura,oraChiusura,iban.getText());
                ChangePage istanza=ChangePage.getChangePage();
                istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/AggiungiFoto.fxml", this.id,richiesta,null,null);

            }catch(IllegalArgumentException exc){
                OrarioException orarioException=new OrarioException();
                GestoreEccezioni.getInstance().handleException(orarioException);

            }catch(SystemException exc1){
                GestoreEccezioni.getInstance().handleException(e);
            }

        }

    }


}
