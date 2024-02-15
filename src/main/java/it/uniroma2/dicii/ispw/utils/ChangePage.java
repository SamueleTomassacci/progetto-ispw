package it.uniroma2.dicii.ispw.utils;

import it.uniroma2.dicii.ispw.Main;
import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePage {

    private static ChangePage istanza=null;
    protected ChangePage(){}
    public static ChangePage getChangePage(){
        if (ChangePage.istanza == null) {
            ChangePage.istanza = new ChangePage();
        }
        return istanza;
    }
    private Stage stage;
    public void cambiaPagina(String fxml, IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto) throws IOException, SystemException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(loader.load(), 1200, 760);
        ControllerGrafico controller=loader.getController();    //Uso del polimorfismo, uso una variabile di tipo ControllerGrafico (superclasse)
        controller.inizializza(id,campoSenzaFotoBean, foto);                             //alla quale in base al pagina caricata associo l'istanza di uno dei controller grafici figli
        this.stage.setScene(scene);                                  //l'operazione inizializza quindi avrà comportamenti diversi in base all'istanza
        this.stage.show();

    }

    public void setStage(Stage stageParam){
        this.stage=stageParam;
    }

    public Stage getStage(){
        return this.stage;
    }
}
