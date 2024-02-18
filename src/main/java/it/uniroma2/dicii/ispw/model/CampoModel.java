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
    int num;
    public CampoModel(String nomeCampo, String indirizzo, int tariffa, Time orarioApertura, Time orarioChiusura, String iban, File immagine) {
        this.nomeCampo = nomeCampo;
        this.indirizzo = indirizzo;
        this.tariffa = tariffa;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
        this.iban = iban;
        this.immagine = immagine;
    }
    public CampoModel(CampoBean campo){
        this.nomeCampo=campo.getNomeCampo();
        this.indirizzo=campo.getIndirizzo();
        this.tariffa= campo.getTariffa();
        this.orarioApertura=campo.getOrarioApertura();
        this.orarioChiusura=campo.getOrarioChiusura();
        this.iban=campo.getIban();
        this.immagine=campo.getImmagine();
        this.num=campo.getNum();
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
    public int numeroCampo(){
        return this.num;
    }
    public void impostaNumeroCampo(int num){
        this.num=num;
    }
}
