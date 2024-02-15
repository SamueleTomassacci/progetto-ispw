package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;

import java.io.File;
import java.sql.Time;

public class CampoBean {
    private String nomeCampo;
    private String indirizzo;
    private int tariffa;
    private Time orarioApertura;
    private Time orarioChiusura;
    private String iban;
    private File immagine;


    public CampoBean(CampoSenzaFotoBean richiesta, FotoBean foto){
        this.nomeCampo= richiesta.getNome();;
        this.indirizzo=richiesta.getPosizione();
        this.tariffa=richiesta.getCosto();
        this.orarioApertura=richiesta.getApertura();
        this.orarioChiusura=richiesta.getChiusura();
        this.iban=richiesta.getPagamento();
        this.immagine=foto.getFoto();
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
