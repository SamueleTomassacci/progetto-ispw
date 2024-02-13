package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.model.GestoreModel;

public class GestoreBean extends UserBean {

    public GestoreBean(GestoreModel gestore){
        super(gestore.getUsername(), gestore.getEmail());
    }


}
