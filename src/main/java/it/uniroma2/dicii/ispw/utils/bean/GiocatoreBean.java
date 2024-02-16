package it.uniroma2.dicii.ispw.utils.bean;


import it.uniroma2.dicii.ispw.model.GiocatoreModel;

public class GiocatoreBean extends UserBean {
    private int altezza;
    private int eta;
    private String ruoloBasket;


    public GiocatoreBean(GiocatoreModel giocatore){
        super(giocatore.getUsername(), giocatore.getEmail());
        this.altezza = giocatore.caratteristicaAltezza();
        this.eta = giocatore.caratteristicaEta();
        this.ruoloBasket = giocatore.caratteristicaRuoloBasket();
    }

}
