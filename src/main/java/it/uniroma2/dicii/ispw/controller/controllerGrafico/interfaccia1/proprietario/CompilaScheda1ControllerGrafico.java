package it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1.proprietario;

import it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1.ControllerGrafico;
import it.uniroma2.dicii.ispw.utils.ChangePage;
import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.Interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.Interfaccia1.RichiestaCampoSenzaFotoBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Time;

/* Eccezioni da gestire:
   --Alcuni campi che non sono stati riempiti
   --Orari non nel formato giusto
   --Indirizzo del campo non esistente

 */

public class CompilaScheda1ControllerGrafico extends ControllerGrafico {
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField nomeCampo;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField tariffa;
    @FXML
    private TextField apertura;
    @FXML
    private TextField chiusura;
    @FXML
    private TextField iban;
    private IdSessioneBean id;
    @Override
    public void inizializza(IdSessioneBean id, RichiestaCampoSenzaFotoBean campoSenzaFotoBean, FotoBean foto){
        this.id=id;
    }

    public void backHome() throws IOException {    //vedi come gestire eccezione
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/homePage.fxml", this.id,null,null);
    }

    public void clickAvanti() throws IOException {

        RichiestaCampoSenzaFotoBean richiesta=new RichiestaCampoSenzaFotoBean(nomeCampo.getText(),indirizzo.getText(), Integer.valueOf(tariffa.getText()), Time.valueOf(apertura.getText()),Time.valueOf(chiusura.getText()),iban.getText());
        ChangePage istanza=ChangePage.getChangePage();
        istanza.cambiaPagina("/it/uniroma2/dicii/ispw/interfacce/interfaccia1/proprietario/aggiungi_campo/AggiungiFoto.fxml", this.id,richiesta,null);
    }

}
