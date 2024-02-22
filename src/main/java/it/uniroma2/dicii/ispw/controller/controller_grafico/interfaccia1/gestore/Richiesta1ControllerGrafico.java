package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.gestore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.PaneBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Richiesta1ControllerGrafico {
    @FXML
    private Label campo;
    @FXML
    private Label indirizzo;
    @FXML
    private Label tariffa;
    @FXML
    private Label orarioApertura;
    @FXML
    private Label orarioChiusura;
    @FXML
    private ImageView immagine;
    private CampoBean campoBean;
    private GestisciRichieste1ControllerGrafico controller;
    private AggiungiCampoControllerApplicativoBase controllerAppl;
    private PaneBean pane;

    public void inizializza(CampoBean campoBean, GestisciRichieste1ControllerGrafico controller, PaneBean pane) {
        this.controllerAppl=new AggiungiCampoControllerApplicativoBase();
        this.controller=controller;
        this.campoBean=campoBean;
        this.pane=pane;
        campo.setText(campoBean.getNomeCampo());
        indirizzo.setText(campoBean.getIndirizzo());
        tariffa.setText(Integer.toString(campoBean.getTariffa()));
        orarioApertura.setText(campoBean.getOrarioApertura().toString());
        orarioChiusura.setText(campoBean.getOrarioChiusura().toString());
        immagine.setImage(new Image(campoBean.getImmagine().toURI().toString()));

    }

    public void accetta() throws SystemException {
        this.controller.elimina(this.pane);
        this.controllerAppl.accetta(this.campoBean);
    }
    public void rifiuta() throws SystemException {
        this.controller.elimina(this.pane);
        this.controllerAppl.rifiuta(this.campoBean);
    }


}
