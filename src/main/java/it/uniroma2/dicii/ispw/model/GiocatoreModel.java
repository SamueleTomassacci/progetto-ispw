package it.uniroma2.dicii.ispw.model;

public class GiocatoreModel extends UserModel {

    int altezza;
    int eta;
    String ruoloBasket;

    public GiocatoreModel(String username, String email, int codice, int altezza, int eta, String ruoloBasket){
        super(username,email,codice);
        this.altezza = altezza;
        this.eta = eta;
        this.ruoloBasket = ruoloBasket;
    }
    public GiocatoreModel(String username, String email, int codice){
       super(username,email,codice);
    }


    public GiocatoreModel(){}

}
