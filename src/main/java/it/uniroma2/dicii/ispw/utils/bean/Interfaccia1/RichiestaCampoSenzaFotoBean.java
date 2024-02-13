package it.uniroma2.dicii.ispw.utils.bean.Interfaccia1;

import java.sql.Time;

public class RichiestaCampoSenzaFotoBean {
    private String nomeCampo;
    private String indirizzo;
    private int tariffa;
    private Time orarioApertura;
    private Time orarioChiusura;

    //private Byte[] immagine;    Per fare la differenza tra le due interfacce, qui li gestisco come separati quindi due bean diversi, nell'altra interfaccia invece unico bean
    private String iban;

    public RichiestaCampoSenzaFotoBean(String nomeCampo, String indirizzo, int tariffa,Time orarioApertura, Time orarioChiusura,String iban){
        this.nomeCampo=nomeCampo;
        this.indirizzo=indirizzo;
        this.tariffa=tariffa;
        this.orarioApertura=orarioApertura;
        this.orarioChiusura=orarioChiusura;
        this.iban=iban;
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

}