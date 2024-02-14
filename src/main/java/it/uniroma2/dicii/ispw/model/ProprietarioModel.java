package it.uniroma2.dicii.ispw.model;

import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;

public class ProprietarioModel extends UserModel{
    private String nome;
    private String cognome;
    private int vip;


    public ProprietarioModel(ProprietarioBean proprietario){
        super(proprietario.getUsername(),proprietario.getEmail());
        this.nome=proprietario.getNome();
        this.cognome=proprietario.getCognome();
        this.vip= proprietario.getVip();
    }

    public ProprietarioModel(){}

    private boolean isVip(){
        return this.vip==1;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
    public int getVip() {
        return this.vip;
    }


}
