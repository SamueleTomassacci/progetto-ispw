package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.model.CampoModel;
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
    private int num=1;
    private int tentativo;


    public CampoBean(CampoSenzaFotoBean richiesta, FotoBean foto){
        this.nomeCampo= richiesta.getNome();
        this.indirizzo=richiesta.getPosizione();
        this.tariffa=richiesta.getCosto();
        this.orarioApertura=richiesta.getApertura();
        this.orarioChiusura=richiesta.getChiusura();
        this.iban=richiesta.getPagamento();
        this.immagine=foto.getFoto();
    }

    public CampoBean(CampoModel campo){
        this.nomeCampo=campo.nomeAttuale();
        this.indirizzo=campo.recuperaIndirizzo();
        this.tariffa=campo.costoOrario();
        this.orarioApertura=campo.inizioAttivita();
        this.orarioChiusura=campo.fineAttivita();
        this.iban=campo.credPagamento();
        this.immagine=campo.recuperaImmagine();
        this.num=campo.numeroCampo();

    }
    public CampoBean(){}
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
    public void setNomeCampo(String nome){
        this.nomeCampo=nome;
    }

    public void setNum(int i){
        this.num=i;
    }
    public int getNum(){
        return this.num;
    }
    public int getTentativo(){
        return this.tentativo;
    }
    public void setTentativo(int i){
        this.tentativo=i;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setTariffa(int tariffa) {
        this.tariffa = tariffa;
    }

    public void setOrarioApertura(Time orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public void setOrarioChiusura(Time orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setImmagine(File immagine) {
        this.immagine = immagine;
    }


}
