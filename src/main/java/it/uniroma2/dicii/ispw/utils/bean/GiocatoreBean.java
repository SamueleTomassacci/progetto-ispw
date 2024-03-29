package it.uniroma2.dicii.ispw.utils.bean;


import it.uniroma2.dicii.ispw.model.GiocatoreModel;

public class GiocatoreBean extends UserBean {
    private final int altezza;
    private final int eta;
    private final String ruoloBasket;


    public GiocatoreBean(GiocatoreModel giocatore){
        super(giocatore.getUsername(), giocatore.getEmail());
        this.altezza = giocatore.caratteristicaAltezza();
        this.eta = giocatore.caratteristicaEta();
        this.ruoloBasket = giocatore.caratteristicaRuoloBasket();
    }

    public String getRuoloBasket(){
        return ruoloBasket;
    }
    public int getAltezza(){
        return altezza;
    }
    public int getEta(){
        return eta;
    }

}
