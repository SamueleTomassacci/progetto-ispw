package it.uniroma2.dicii.ispw.utils.bean;


import it.uniroma2.dicii.ispw.model.ProprietarioModel;

public class ProprietarioBean extends UserBean {
    public ProprietarioBean(ProprietarioModel proprietario){
        super(proprietario.getUsername(), proprietario.getEmail());
    }
}
