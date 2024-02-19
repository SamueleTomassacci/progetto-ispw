package it.uniroma2.dicii.ispw.model.partita;

import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore.ListaPartiteObserver;
import it.uniroma2.dicii.ispw.utils.bean.PartitaBean;


import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<ListaPartiteObserver> observers;

    public Subject(){
        this.observers = new ArrayList<>();
    }
    public void register(ListaPartiteObserver observer){
        observers.add(observer);
    }

    public void unregister(ListaPartiteObserver observer){
        observers.remove(observer);
    }
    public void notifyObservers(PartitaModel partita) {
        PartitaBean bean = new PartitaBean(partita);
        for( ListaPartiteObserver observer : observers){
            observer.update(bean);
        }
    }

}
