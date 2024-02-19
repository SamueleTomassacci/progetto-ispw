package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.LoginControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.Role;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.LoginException;
import it.uniroma2.dicii.ispw.utils.exceptions.RuoloNonSelezionatoException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Accesso2ControllerGrafico extends ControllerGrafico {
    private CredentialsBean cred;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred){
        this.cred=cred;
    }

    public void login(){
        this.cred.setUsername(username.getText());
        this.cred.setPassword(password.getText());

        LoginControllerApplicativo loginController = new LoginControllerApplicativo();

        try {

            loginController.login(cred);

            ChangePage istanza = ChangePage.getChangePage();
            switch (cred.getRole()) {
                case PROPRIETARIO ->
                        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/proprietario/homePage.fxml", new IdSessioneBean(cred.getIdSession()), null, null,null);
                case GIOCATORE -> {
                    break;
                }
                case GESTORE ->{
                    break;
                }
            }
        } catch (LoginException | SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

}
