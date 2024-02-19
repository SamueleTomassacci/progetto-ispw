package it.uniroma2.dicii.ispw.utils.bean;

import java.util.ArrayList;
import java.util.List;

public class ListaPartiteBean {
    private List<PartitaBean> lista;
    public ListaPartiteBean() {
        this.lista = new ArrayList<>();
    }
    public void add(PartitaBean partita){
        this.lista.add(partita);
    }

    public List<PartitaBean> getLista() {
        return lista;
    }
}
