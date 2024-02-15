package it.uniroma2.dicii.ispw.model;

import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;

public class ProprietarioModel extends UserModel{
    private String nome;
    private String cognome;
    private int vip;

    public ProprietarioModel(String username,String pass,int id,String nome, String cognome,int vip){
        super(username,pass, id);
        this.nome=nome;
        this.cognome=cognome;
        this.vip=vip;
    }



    public ProprietarioModel(ProprietarioBean proprietario){
        super(proprietario.getUsername(),proprietario.getEmail());
        this.nome=proprietario.getNome();
        this.cognome=proprietario.getCognome();
        this.vip= proprietario.getVip();
    }

    public ProprietarioModel(){}

    public boolean isVip(){
        return this.vip==1;
    }


    public String credenzialeNome() {
        return nome;
    }



    public String credenzialeCognome() {
        return cognome;
    }





}
