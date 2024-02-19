package it.uniroma2.dicii.ispw.model.partita;

public class PartitaCampoModel {
    private String nome;
    private String indirizzo;

    public PartitaCampoModel(String nome, String indirizzo){
        this.nome = nome;
        this.indirizzo = indirizzo;
    }
    public String recuperaNome() {
        return nome;
    }

    public String recuperaIndirizzo() {
        return indirizzo;
    }

}
