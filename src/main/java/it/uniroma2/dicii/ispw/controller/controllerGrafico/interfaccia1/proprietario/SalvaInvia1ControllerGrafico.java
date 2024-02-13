package it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.Interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.Interfaccia1.RichiestaCampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SalvaInvia1ControllerGrafico extends ControllerGrafico {          //Questo è un commento per github
    private IdSessioneBean id;
    private RichiestaCampoSenzaFotoBean campoSenzaFotoBean;
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

    @Override
    public void inizializza(IdSessioneBean id, RichiestaCampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto){
        this.id=id;
        this.campoSenzaFotoBean=campoSenzaFotoBean;
        this.foto=foto;
        nome.setText(campoSenzaFotoBean.getNomeCampo());
        indirizzo.setText(campoSenzaFotoBean.getIndirizzo());
        tariffa.setText(Integer.toString(campoSenzaFotoBean.getTariffa()));
        apertura.setText(campoSenzaFotoBean.getOrarioApertura().toString());
        chiusura.setText(campoSenzaFotoBean.getOrarioChiusura().toString());
        iban.setText(campoSenzaFotoBean.getIban());
    }

    public void backHome() throws IOException {    //vedi come gestire eccezione
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/Interfacce/Interfaccia1/Proprietario/homePage.fxml", this.id,null,null);
    }

    public void back() throws IOException {    //vedi come gestire eccezione
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/Interfacce/Interfaccia1/Proprietario/AggiungiCampo/AggiungiFoto.fxml", this.id,campoSenzaFotoBean,null);
    }



}
