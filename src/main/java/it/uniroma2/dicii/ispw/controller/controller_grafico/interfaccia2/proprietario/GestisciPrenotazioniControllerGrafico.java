package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GestisciPrenotazioniControllerGrafico extends ControllerGrafico {

    private IdSessioneBean id;
    @FXML
    private Button profilo;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) throws SystemException {
        this.id=id;
        SessionManager manager=SessionManager.getSessionManager();
        Session session=manager.getSessionFromId(id);
        ProprietarioBean proprietarioBean=session.getProprietarioBean();
        String nome=proprietarioBean.getUsername();
        profilo.setText(nome);
    }
}
