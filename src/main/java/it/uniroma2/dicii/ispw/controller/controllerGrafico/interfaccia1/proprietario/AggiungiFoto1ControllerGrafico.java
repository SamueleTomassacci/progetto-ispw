package it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.Interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.Interfaccia1.RichiestaCampoSenzaFotoBean;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/*
  Gestire l'eccezione in cui l'utente non mette nessuna foto
 */

public class AggiungiFoto1ControllerGrafico extends ControllerGrafico {
    @FXML
    private ImageView trascina;
    @FXML
    private ImageView mostra;
    private IdSessioneBean id;
    private RichiestaCampoSenzaFotoBean campoSenzaFotoBean;

    private File immagine;
    @Override
    public void inizializza(IdSessioneBean id, RichiestaCampoSenzaFotoBean campoSenzaFotoBean,FotoBean foto){
        this.id=id;
        this.campoSenzaFotoBean=campoSenzaFotoBean;
    }
    public void inserisci(DragEvent event) {
        if (event.getGestureSource() != this.trascina &&
                event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    public void rilascia(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            String imagePath = db.getFiles().get(0).toURI().toString();
            Image image = new Image(imagePath);
            this.immagine=db.getFiles().get(0).getAbsoluteFile();
            this.mostra.setImage(image);
        }
        event.setDropCompleted(success);
        event.consume();
    }

    public void seleziona() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un'immagine");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Immagine", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        ChangePage istanza=ChangePage.getChangePage();
        Stage stage=istanza.getStage();
        File selectedFile = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        this.immagine=selectedFile;
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            this.mostra.setImage(image);
        }
    }
    public void backHome() throws IOException {    //vedi come gestire eccezione
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/Interfacce/Interfaccia1/Proprietario/homePage.fxml", this.id,null,null);
    }
    public void back() throws IOException {    //vedi come gestire eccezione
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/Interfacce/Interfaccia1/Proprietario/AggiungiCampo/compilaScheda.fxml", this.id,null,null);
    }

    public void avanti() throws IOException {    //vedi come gestire eccezione

        FotoBean foto= new FotoBean(this.immagine);

        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/Interfacce/Interfaccia1/Proprietario/AggiungiCampo/salvaInvia.fxml", this.id,campoSenzaFotoBean,foto);
    }


}
