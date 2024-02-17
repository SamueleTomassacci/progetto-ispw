package it.uniroma2.dicii.ispw.model;

public class NomeCampoModel {
    private String nome;
    private String indirizzo;

    public NomeCampoModel(String nome, String indirizzo){
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNome() {
        return nome;
    }
}
