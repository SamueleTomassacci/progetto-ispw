/*module com.example.progettoispw {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens it.uniroma2.dicii.ispw to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controllerGrafico.interfaccia1 to javafx.fxml;
    exports it.uniroma2.dicii.ispw;
}
*/
module it.uniroma2.dicii.ispw {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.mail;
    requires com.opencsv;

    opens it.uniroma2.dicii.ispw to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1 to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.proprietario to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.gestore to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2 to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario.aggiungi_campo to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.gestore to javafx.fxml;
    opens it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore to javafx.fxml;
    opens it.uniroma2.dicii.ispw.utils.bean to javafx.base;
    exports it.uniroma2.dicii.ispw;
    exports it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2;
    exports it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.proprietario;
    exports it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia2.giocatore;
    exports it.uniroma2.dicii.ispw.utils.exceptions;
    exports it.uniroma2.dicii.ispw.utils.dao;
    exports it.uniroma2.dicii.ispw.model;
    exports it.uniroma2.dicii.ispw.utils.bean;
    exports it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;
    exports it.uniroma2.dicii.ispw.utils.db;
    exports it.uniroma2.dicii.ispw.model.partita;

}