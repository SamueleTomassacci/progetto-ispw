package it.uniroma2.dicii.ispw.utils.bean;


import it.uniroma2.dicii.ispw.model.GiocatoreModel;

public class GiocatoreBean extends UserBean {
    public GiocatoreBean(GiocatoreModel giocatore){
        super(giocatore.getUsername(), giocatore.getEmail());
    }
}
