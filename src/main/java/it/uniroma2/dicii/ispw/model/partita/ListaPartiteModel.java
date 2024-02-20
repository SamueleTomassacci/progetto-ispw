package it.uniroma2.dicii.ispw.model.partita;


import java.util.ArrayList;
import java.util.List;

public class ListaPartiteModel extends Subject{
    private List<PartitaModel> lista;

    public ListaPartiteModel() {
        // creazione di una lista di osservatori vuota
        lista = new ArrayList<>();
    }

    public void addRichiestaPartita(PartitaModel partita){
        lista.add(partita);
        // notifica agli osservatori
        notifyObservers(partita);
    }

    public void setStatoPartita(PartitaModel updatedPartita) {
        // Cerca la partita nella lista
        for (PartitaModel partita : lista) {
            if (partita.recuperaOrarioInizio().equals(updatedPartita.recuperaOrarioInizio()) &&
                    partita.recuperaNome().equals(updatedPartita.recuperaNome()) &&
                    partita.recuperaIndirizzo().equals(updatedPartita.recuperaIndirizzo()) &&
                    partita.recuperaData().equals(updatedPartita.recuperaData())) {
                // Aggiorna lo stato della partita
                partita.impostaStato(updatedPartita.infoStato());
                // Notifica agli osservatori
                notifyObservers(partita);
                break;
            }
        }
    }

    public List<PartitaModel> recuperaLista(){
        return lista;
    }

}
