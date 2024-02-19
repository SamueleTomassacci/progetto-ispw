package it.uniroma2.dicii.ispw.utils.bean;

import java.util.ArrayList;
import java.util.List;

public class ListaNomeCampoBean {
    private List<PartitaCampoBean> lista;
    public ListaNomeCampoBean() {
        this.lista = new ArrayList<>();
    }
    public void add(PartitaCampoBean campo){
        this.lista.add(campo);
    }

    public List<PartitaCampoBean> getLista() {
        return lista;
    }
}
