package it.uniroma2.dicii.ispw.utils.bean;

import java.util.ArrayList;
import java.util.List;

public class ListaNomeCampoBean {
    private List<NomeCampoBean> lista;
    public ListaNomeCampoBean() {
        this.lista = new ArrayList<>();
    }
    public void add(NomeCampoBean campo){
        this.lista.add(campo);
    }

    public List<NomeCampoBean> getLista() {
        return lista;
    }
}
