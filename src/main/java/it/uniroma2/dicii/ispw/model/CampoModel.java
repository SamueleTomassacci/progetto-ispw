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
        this.tariffa= campo.getTariffa();;
        this.orarioApertura=campo.getOrarioApertura();
        this.orarioChiusura=campo.getOrarioChiusura();
        this.iban=campo.getIban();
        this.immagine=campo.getImmagine();
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getTariffa() {
        return tariffa;
    }

    public Time getOrarioApertura() {
        return orarioApertura;
    }

    public Time getOrarioChiusura() {
        return orarioChiusura;
    }

    public String getIban() {
        return iban;
    }

    public File getImmagine() {
        return immagine;
    }
}
