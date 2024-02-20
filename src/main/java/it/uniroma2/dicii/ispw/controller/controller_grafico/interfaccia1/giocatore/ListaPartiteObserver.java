package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore;

import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;


public interface ListaPartiteObserver {
    // operarazione di aggiornamento di una partita
    void update (PartitaBean partita);
}