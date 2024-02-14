package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.VipControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto){
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
        if(proprietario.getVip()==1){                                    //Vedi se lo puoi fare con un eccezione
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Attenzione hai già fatto l'upgrade a vip!");
            alert.showAndWait();
        }
        else {
            VipControllerApplicativo controller = new VipControllerApplicativo();
            controller.upgradeVip(proprietario);
            int vip = 1;
            proprietario.setVip(vip);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upgrade");
            alert.setHeaderText(null);
            alert.setContentText("Upgrade a vip eseguito!");
            alert.showAndWait();
        }


    }


}
