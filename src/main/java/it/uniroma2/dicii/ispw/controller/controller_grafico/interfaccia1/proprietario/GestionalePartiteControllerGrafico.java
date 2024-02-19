package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita.CreaPartitaControllerApplicativo;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class GestionalePartiteControllerGrafico extends ControllerGrafico {
    @FXML
    public Text username;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public VBox vbox;

    private IdSessioneBean id;
    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) throws SystemException {
        // imposto nome utente nella pagina

       try {
           this.id = id;
           SessionManager manager = SessionManager.getSessionManager();
           Session session = manager.getSessionFromId(id);
           ProprietarioBean proprietario = session.getProprietarioBean();
           username.setText(proprietario.getUsername());

           // inizializzazione delle richieste

           aggiornaLista();
       }catch(IOException e){
           GestoreEccezioni.getInstance().handleException(new SystemException());
       }
    }

    public void clickBack() throws SystemException, IOException {
        ChangePage istanza = ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml",this.id,null,null,null);
    }

    public void aggiornaLista() throws SystemException, IOException {
        // Inizializzazione righe della tabella
        UserBean user = new UserBean(username.getText());
        CreaPartitaControllerApplicativo controllerApplicativo = new CreaPartitaControllerApplicativo();
        List<PartitaBean> lista = controllerApplicativo.inizializzaRichiesteProprietario(user);
        for (PartitaBean partita : lista){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/crea_partita/rigaRichiestaProprietario.fxml"));
            Node rigaRichiesta = loader.load();
            RigaRichiestaPartitaControllerGrafico controller = loader.getController();
            controller.inizializza(this,partita);
            vbox.getChildren().add(rigaRichiesta);
        }
    }

    public void rimuoviRiga(Node node) {
        vbox.getChildren().remove(node);
    }


}