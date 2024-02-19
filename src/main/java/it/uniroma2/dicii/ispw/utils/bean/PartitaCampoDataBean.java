package it.uniroma2.dicii.ispw.utils.bean;

import java.sql.Date;
import java.time.LocalDate;

public class PartitaCampoDataBean extends PartitaCampoBean {
    private LocalDate giorno;

    public PartitaCampoDataBean(String nome, String indirizzo, LocalDate giorno) {
        super(nome, indirizzo);
        this.giorno = giorno;
    }

    public LocalDate getGiorno() {
        return giorno;
    }
}
