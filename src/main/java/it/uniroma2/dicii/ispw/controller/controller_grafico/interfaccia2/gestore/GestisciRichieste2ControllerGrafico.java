package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.gestore;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.gestore.Richiesta1ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.PaneBean;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GestisciRichieste2ControllerGrafico extends ControllerGrafico {

    @FXML
    private VBox box;
    @FXML
    private Button profilo;
    private IdSessioneBean idSession;
    private int numeroRichieste;
    private int cont = 0;
    private List<CampoBean> lista;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto, CredentialsBean cred) {

        this.idSession = id;
        SessionManager manager = SessionManager.getSessionManager();
        Session session = manager.getSessionFromId(id);
        GestoreBean gestoreBean = session.getGestoreBean();
        String nome = gestoreBean.getUsername();
        profilo.setText(nome);

        PaneBean paneBean = null;
        Pane pane = null;

        try {

            AggiungiCampoControllerApplicativoBase controller = new AggiungiCampoControllerApplicativoBase();
            lista = controller.caricaRichieste();

            numeroRichieste = lista.size();

            if (numeroRichieste > 0) {

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/gestore/CampoRichiesta.fxml"));

                CampoBean campo = lista.get(cont);

                pane = loader.load();
                paneBean = new PaneBean(pane);

                Richiesta2ControllerGrafico controllerRichiesta = loader.getController();
                controllerRichiesta.inizializza(campo, this, paneBean);
                box.getChildren().add(pane);
                cont++;
            }

        } catch (IOException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            GestoreEccezioni.getInstance().handleException(e);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }

    }

    public void logout() {
        try {
            SessionManager.getSessionManager().rimuoviSessione(idSession);
            ChangePage page = ChangePage.getChangePage();

            Stage stageprim = page.getStage();
            FXMLLoader loaderfxml = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/loginPage2.fxml"));
            Scene scene = null;

            scene = new Scene(loaderfxml.load(), 1200, 760);

            stageprim.setScene(scene);
            stageprim.show();

            }catch (IOException e) {
                SystemException exception = new SystemException();

                exception.initCause(e);
                GestoreEccezioni.getInstance().handleException(e);
        }

    }


    public void elimina(PaneBean pane) {
        box.getChildren().remove(pane.getPane());

        if (cont < numeroRichieste) {
            Pane paneDaInserire = null;
            PaneBean paneBean = null;

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia2/gestore/CampoRichiesta.fxml"));

                CampoBean campo = lista.get(cont);

                paneDaInserire = fxmlLoader.load();
                paneBean = new PaneBean(paneDaInserire);

                Richiesta2ControllerGrafico controllerRichiesta = fxmlLoader.getController();
                controllerRichiesta.inizializza(campo, this, paneBean);
                box.getChildren().add(paneDaInserire);
                cont++;
            } catch (IOException e) {
                SystemException exception = new SystemException();
                exception.initCause(e);
                GestoreEccezioni.getInstance().handleException(e);
            }
        }
    }
}
