package it.uniroma2.dicii.ispw.model;

public class ProprietarioModel extends UserModel{
    private String nome;
    private String cognome;
    private int vip;
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
