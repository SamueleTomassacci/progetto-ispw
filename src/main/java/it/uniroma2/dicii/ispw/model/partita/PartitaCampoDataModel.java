package it.uniroma2.dicii.ispw.model.partita;


import java.time.LocalDate;

public class PartitaCampoDataModel extends PartitaCampoModel {
    private LocalDate giorno;

    public PartitaCampoDataModel(String nome, String indirizzo, LocalDate giorno) {
        super(nome, indirizzo);
        this.giorno = giorno;
    }

    public LocalDate recuperaData(){
        return giorno;
    }

}
