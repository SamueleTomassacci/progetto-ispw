package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.model.partita.PartitaCampoModel;

public class PartitaCampoBean {
    private String nome;
    private String indirizzo;


    public PartitaCampoBean(PartitaCampoModel campo) {
        this.nome = campo.recuperaNome();
        this.indirizzo = campo.recuperaIndirizzo();
    }

    public PartitaCampoBean(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }
    @Override
    public String toString() {
        return nome+" "+indirizzo;
    }

}
