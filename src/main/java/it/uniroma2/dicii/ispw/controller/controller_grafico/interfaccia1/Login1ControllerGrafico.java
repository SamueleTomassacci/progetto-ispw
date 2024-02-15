package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1;

import it.uniroma2.dicii.ispw.controller.controller_applicativo.LoginControllerApplicativo;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.Role;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.sql.SQLException;

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
    private void clickAccesso() throws SystemException, IOException, SQLException {   //Da ricontrollare le eccezioni
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
        loginController.login(cred);
        ChangePage istanza=ChangePage.getChangePage();
        switch (cred.getRole()) {
            case PROPRIETARIO ->
                    istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", new IdSessioneBean(cred.getIdSession()),null,null);
            case GIOCATORE -> {
                break;     //sostiuire con home page del giocatore
            }
            case GESTORE ->{
                istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/gestore/homePage.fxml", new IdSessioneBean(cred.getIdSession()),null,null);
            }
        }
    }
}





