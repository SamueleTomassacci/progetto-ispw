package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.gestore;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.PaneBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestisciRichieste1ControllerGrafico extends ControllerGrafico {
    private IdSessioneBean id;
    @FXML
    private VBox box;

    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto) throws IOException, SystemException {

        this.id=id;
        AggiungiCampoControllerApplicativoBase controller= new AggiungiCampoControllerApplicativoBase();
        List<CampoBean> lista=controller.caricaRichieste();

        PaneBean paneBean=null;
        Pane pane=null;
        for(CampoBean campo:lista) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/gestore/gestisci/CampoRichiesta.fxml"));
            pane = loader.load();
            paneBean=new PaneBean(pane);

            Richiesta1ControllerGrafico controllerRichiesta = loader.getController();
            controllerRichiesta.inizializza(campo, this, paneBean);
            box.getChildren().add(pane);
        }

    }

    public void back() throws IOException, SystemException {
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/gestore/homePage.fxml",this.id,null,null);
    }

    public void elimina(PaneBean pane){
        box.getChildren().remove(pane.getPane());
    }

}
