package it.uniroma2.dicii.ispw.model.partita;

import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;
import it.uniroma2.dicii.ispw.utils.bean.RichiestaPartitaBean;

import java.time.LocalDate;
import java.time.LocalTime;


public class PartitaModel extends Subject{
    private String nomeCampo;
    private String indirizzoCampo;
    private LocalDate giorno;
    private String creatore;
    private LocalTime orarioInizio;
    private int numGiocatori;
    private statoPartita stato;


    public PartitaModel(String nome, String indirizzo, LocalDate data){
        this.nomeCampo = nome;
        this.indirizzoCampo = indirizzo;
        this.giorno = data;
    }

    public PartitaModel(String nomeCampo, String indirizzo){
        this.nomeCampo = nomeCampo;
        this.indirizzoCampo = indirizzo;
    }

    public PartitaModel(String nomeCampo, String indirizzoCampo, LocalDate giorno, String creatore, LocalTime orarioInizio, int numGiocatori, statoPartita stato){
        this.nomeCampo = nomeCampo;
        this.indirizzoCampo = indirizzoCampo;
        this.giorno = giorno;
        this.creatore = creatore;
        this.orarioInizio = orarioInizio;
        this.numGiocatori = numGiocatori;
        this.stato = stato;
    }
    public PartitaModel(RichiestaPartitaBean bean) {
        this.nomeCampo = bean.getNome();
        this.indirizzoCampo = bean.getIndirizzo();
        this.giorno = bean.getGiorno();
        this.orarioInizio = bean.getOra();
        this.numGiocatori = bean.getNumGiocatori();
        this.creatore = bean.getCreatore();
        this.stato = statoPartita.PENDENTE;
    }

    public PartitaModel(PartitaBean bean) {
        this.nomeCampo = bean.getNomeCampo();
        this.indirizzoCampo = bean.getIndirizzoCampo();
        this.giorno = bean.getGiorno();
        this.creatore = bean.getCreatore();
        this.orarioInizio = bean.getOraInizio();
        this.numGiocatori = bean.getNumGiocatori();
        this.stato = bean.getStato();
    }

    public LocalTime recuperaOrarioInizio() {
        return orarioInizio;
    }

    public int recuperaNumGiocatori() {
        return numGiocatori;
    }

    public String recuperaCreatore(){
        return creatore;
    }

    public String recuperaNome() { return nomeCampo; }

    public String recuperaIndirizzo() {
        return indirizzoCampo;
    }

    public LocalDate recuperaData() {
        return giorno;
    }

    public statoPartita infoStato() {
        return stato;
    }

    public void impostaStato(statoPartita newstato) {
        this.stato = newstato;
    }
}
