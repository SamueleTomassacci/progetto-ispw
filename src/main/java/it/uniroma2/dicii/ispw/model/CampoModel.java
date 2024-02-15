package it.uniroma2.dicii.ispw.model;

import it.uniroma2.dicii.ispw.utils.bean.CampoBean;

import java.io.File;
import java.sql.Time;

public class CampoModel {
    private String nomeCampo;
    private String indirizzo;
    private int tariffa;
    private Time orarioApertura;
    private Time orarioChiusura;
    private String iban;
    private File immagine;

    public CampoModel(CampoBean campo){
        this.nomeCampo=campo.getNomeCampo();
        this.indirizzo=campo.getIndirizzo();
        this.tariffa= campo.getTariffa();
        this.orarioApertura=campo.getOrarioApertura();
        this.orarioChiusura=campo.getOrarioChiusura();
        this.iban=campo.getIban();
        this.immagine=campo.getImmagine();
    }

    public String nomeAttuale () {
        return nomeCampo;
    }

    public String recuperaIndirizzo() {
        return indirizzo;
    }

    public int costoOrario() {
        return tariffa;
    }

    public Time inizioAttivita() {
        return orarioApertura;
    }

    public Time fineAttivita() {
        return orarioChiusura;
    }

    public String credPagamento() {
        return iban;
    }

    public File recuperaImmagine() {
        return immagine;
    }
}
