package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.FotoMancanteException;
import it.uniroma2.dicii.ispw.utils.exceptions.GestoreEccezioni;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;




public class AggiungiFoto1ControllerGrafico extends ControllerGrafico {
    @FXML
    private ImageView trascina;
    @FXML
    private ImageView mostra;
    private IdSessioneBean id;
    private CampoSenzaFotoBean campoSenzaFotoBean;

    private File immagine;


    @Override
    public void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto, CredentialsBean cred){
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
    public void backHome(){
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }
    public void back(){
        try {
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/compilaScheda.fxml", this.id, null, null,null);
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

    public void avanti(){


        try {
            if (this.immagine == null) {
                throw new FotoMancanteException();
            }

            FotoBean foto = new FotoBean(this.immagine);
            ChangePage istanza = ChangePage.getChangePage();
            istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/salvaInvia.fxml", this.id, campoSenzaFotoBean, foto,null);

        } catch (FotoMancanteException | SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }


}
