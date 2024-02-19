package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.model.partita.PartitaModel;
import it.uniroma2.dicii.ispw.model.partita.statoPartita;

import java.time.LocalDate;
import java.time.LocalTime;

public class PartitaBean {
    private statoPartita stato;
    private String nomeCampo;
    private String indirizzoCampo;
    private LocalDate giorno;
    private LocalTime oraInizio;
    private int numGiocatori;
    private String creatore;
    public PartitaBean(PartitaModel partita){
        this.giorno = partita.recuperaData();
        this.indirizzoCampo = partita.recuperaIndirizzo();
        this.numGiocatori = partita.recuperaNumGiocatori();
        this.stato = partita.infoStato();
        this.nomeCampo = partita.recuperaNome();
        this.oraInizio = partita.recuperaOrarioInizio();
        this.creatore = partita.recuperaCreatore();
    }

    public PartitaBean(String nomeCampo, String indirizzoCampo, LocalDate giorno, LocalTime oraInizio, int numGiocatori, String creatore){
        this.nomeCampo = nomeCampo;
        this.indirizzoCampo = indirizzoCampo;
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.numGiocatori = numGiocatori;
        this.creatore = creatore;
    }

    public statoPartita getStato(){
        return stato;
    }

    public void setStato(statoPartita newstato){
        this.stato = newstato;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public String getIndirizzoCampo() {
        return indirizzoCampo;
    }

    public LocalDate getGiorno() {
        return giorno;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public int getNumGiocatori() {
        return numGiocatori;
    }

    public String getCreatore() {
        return creatore;
    }
}
