package it.uniroma2.dicii.ispw.utils.bean;

import java.util.List;

public class ListaRichiesteCampoBean {
    private List<CampoBean> lista;

    public void add(CampoBean campo){
        this.lista.add(campo);
    }
}
