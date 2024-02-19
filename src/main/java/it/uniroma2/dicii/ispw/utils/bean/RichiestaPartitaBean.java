package it.uniroma2.dicii.ispw.utils.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class RichiestaPartitaBean extends PartitaCampoDataBean{
    private LocalTime ora;
    private int numGiocatori;
    private String creatore;

    public RichiestaPartitaBean(String nome, String indirizzo, LocalDate giorno, LocalTime orarioInizio, int numGiocatori, String creatore) {
        super(nome, indirizzo, giorno);
        this.ora = orarioInizio;
        this.numGiocatori = numGiocatori;
        this.creatore = creatore;
    }

    public LocalTime getOra() {
        return ora;
    }

    public int getNumGiocatori() {
        return numGiocatori;
    }

    public String getCreatore() {
        return creatore;
    }
}
