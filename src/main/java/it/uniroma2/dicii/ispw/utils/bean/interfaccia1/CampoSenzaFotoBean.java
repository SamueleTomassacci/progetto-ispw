package it.uniroma2.dicii.ispw.utils.bean.interfaccia1;

import java.sql.Time;

public class CampoSenzaFotoBean {
    private String nomeCampo;
    private String indirizzo;
    private int tariffa;
    private Time orarioApertura;
    private Time orarioChiusura;

    //private Byte[] immagine;    Per fare la differenza tra le due interfacce, qui li gestisco come separati quindi due bean diversi, nell'altra interfaccia invece unico bean
    private String iban;

    public CampoSenzaFotoBean(String nomeCampo, String indirizzo, int tariffa, Time orarioApertura, Time orarioChiusura, String iban){
        this.nomeCampo=nomeCampo;
        this.indirizzo=indirizzo;
        this.tariffa=tariffa;
        this.orarioApertura=orarioApertura;
        this.orarioChiusura=orarioChiusura;
        this.iban=iban;
    }
    public String getNome() {
        return nomeCampo;
    }

    public String getPosizione() {
        return indirizzo;
    }

    public int getCosto() {
        return tariffa;
    }

    public Time getApertura() {
        return orarioApertura;
    }

    public Time getChiusura() {
        return orarioChiusura;
    }

    public String getPagamento() {
        return iban;
    }

}