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
        this.nomeCampo= richiesta.getNomeCampo();;
        this.indirizzo=richiesta.getIndirizzo();
        this.tariffa=richiesta.getTariffa();
        this.orarioApertura=richiesta.getOrarioApertura();
        this.orarioChiusura=richiesta.getOrarioChiusura();
        this.iban=richiesta.getIban();
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
