package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoVip;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

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

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto){
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

    public void backHome() throws SystemException {
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", this.id,null,null);
    }

    public void back() throws SystemException {
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/AggiungiFoto.fxml", this.id,campoSenzaFotoBean,null);
    }

    public void salva() throws SystemException {

        CampoBean richiesta=new CampoBean(campoSenzaFotoBean,foto);

        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietario=session.getProprietarioBean();

        AggiungiCampoControllerApplicativo controller=new AggiungiCampoControllerApplicativoBase();
        if(proprietario.getVip()==1){
            AggiungiCampoControllerApplicativoVip vip=new AggiungiCampoControllerApplicativoVip(controller);
            vip.inviaRichiestaGestore(richiesta,proprietario);

        }
        else{

            controller.inviaRichiestaGestore(richiesta,proprietario);
        }
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", this.id,null,null);

    }

}
