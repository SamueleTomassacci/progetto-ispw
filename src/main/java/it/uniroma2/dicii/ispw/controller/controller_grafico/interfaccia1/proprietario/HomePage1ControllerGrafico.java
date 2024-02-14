package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.diventaVipControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.RichiestaCampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class HomePage1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    @FXML
    private Label user;
    @FXML
    private Button aggiungi;
    @FXML
    private Button gestisci;
    @Override
    public void inizializza(IdSessioneBean id, RichiestaCampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto){
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietario=session.getProprietarioBean();
        String nome=proprietario.getUsername();
        user.setText("Bentornato "+nome+"!");
    }

    public void clickAggiungi() throws IOException {     //Vedere come gestire l'eccezione
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/compilaScheda.fxml",this.id,null,null);
    }

    public void upgradeVip() throws SystemException, SQLException {
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietario=session.getProprietarioBean();
        diventaVipControllerApplicativo controller= new diventaVipControllerApplicativo();
        controller.upgradeVip(proprietario);


    }


}
