package it.uniroma2.dicii.ispw.model;

public class GiocatoreModel extends UserModel {

    private int altezza;
    private int eta;
    private String ruoloBasket;


    public GiocatoreModel(String username, int idUser, int altezza, int eta, String ruoloBasket) {
        super(username,null,idUser);
        this.altezza = altezza;
        this.eta = eta;
        this.ruoloBasket = ruoloBasket;
    }

    public int caratteristicaAltezza() {
        return altezza;
    }
    public int caratteristicaEta(){
        return eta;
    }
    public String caratteristicaRuoloBasket(){
        return ruoloBasket;
    }
}
