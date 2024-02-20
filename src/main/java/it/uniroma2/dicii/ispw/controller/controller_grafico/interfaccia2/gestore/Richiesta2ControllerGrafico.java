package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.gestore;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;

import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.PaneBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Richiesta2ControllerGrafico {
    @FXML
    private Label campoLabel;
    @FXML
    private Label indirizzoLabel;
    @FXML
    private Label tariffaLabel;
    @FXML
    private Label orarioAperturaLabel;
    @FXML
    private Label orarioChiusuraLabel;
    @FXML
    private ImageView img;
    private CampoBean campo;
    private GestisciRichieste2ControllerGrafico controller;
    private AggiungiCampoControllerApplicativoBase controllerAppl;
    private PaneBean pane;

    public void inizializza(CampoBean campoBean, GestisciRichieste2ControllerGrafico controller, PaneBean pane) {
        this.controllerAppl=new AggiungiCampoControllerApplicativoBase();
        this.controller=controller;
        this.campo=campoBean;
        this.pane=pane;
        campoLabel.setText(campoBean.getNomeCampo());
        indirizzoLabel.setText(campoBean.getIndirizzo());
        tariffaLabel.setText(Integer.toString(campoBean.getTariffa()));
        orarioAperturaLabel.setText(campoBean.getOrarioApertura().toString());
        orarioChiusuraLabel.setText(campoBean.getOrarioChiusura().toString());
        img.setImage(new Image(campoBean.getImmagine().toURI().toString()));
    }

    public void accetta() throws SystemException {
        this.controller.elimina(this.pane);
        this.controllerAppl.accetta(this.campo);
    }
    public void rifiuta() throws SystemException {
        this.controller.elimina(this.pane);
        this.controllerAppl.rifiuta(this.campo);
    }


}

