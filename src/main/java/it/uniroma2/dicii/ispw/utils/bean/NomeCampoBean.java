package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.model.NomeCampoModel;

public class NomeCampoBean {
    private String nome;
    private String indirizzo;

    public NomeCampoBean(String nome, String indirizzo){
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public NomeCampoBean(NomeCampoModel campo) {
        this.nome = campo.getNome();
        this.indirizzo = campo.getIndirizzo();
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
