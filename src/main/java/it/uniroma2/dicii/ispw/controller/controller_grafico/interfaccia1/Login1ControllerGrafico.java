package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.LoginControllerApplicativo;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.Role;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.LoginException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;



public class Login1ControllerGrafico {
    @FXML
    private ToggleButton proprietario;
    @FXML
    private ToggleButton giocatore;
    @FXML
    private ToggleButton gestore;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button registrazione;
    @FXML
    private Button accesso;
    @FXML
    private Label avviso;

    @FXML
    private void clickAccesso() {
        CredentialsBean cred = new CredentialsBean(username.getText(), password.getText());
        if (proprietario.isSelected()) {
            cred.setRole(Role.PROPRIETARIO);

        } else if (giocatore.isSelected()) {
            cred.setRole(Role.GIOCATORE);

        } else if (gestore.isSelected()) {
            cred.setRole(Role.GESTORE);

        } else {
            avviso.setText("Attenzione, seleziona un ruolo!");
            return;
        }
        LoginControllerApplicativo loginController = new LoginControllerApplicativo();

        try {

            loginController.login(cred);

            ChangePage istanza = ChangePage.getChangePage();
            switch (cred.getRole()) {
                case PROPRIETARIO ->
                        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", new IdSessioneBean(cred.getIdSession()), null, null);
                case GIOCATORE ->
                        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/giocatore/homePage.fxml", new IdSessioneBean(cred.getIdSession()), null, null);
                case GESTORE ->
                        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/gestore/homePage.fxml", new IdSessioneBean(cred.getIdSession()), null, null);
            }
        } catch (LoginException | SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
}





