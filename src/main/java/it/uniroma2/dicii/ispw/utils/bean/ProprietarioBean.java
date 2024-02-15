package it.uniroma2.dicii.ispw.utils.bean;


import it.uniroma2.dicii.ispw.model.ProprietarioModel;

public class ProprietarioBean extends UserBean {
    private String nome;
    private String cognome;
    private int vip;
    public ProprietarioBean(ProprietarioModel proprietario){
        super(proprietario.getUsername(), proprietario.getEmail());
        this.nome=proprietario.credenzialeNome();
        this.cognome=proprietario.credenzialeCognome();
        if(proprietario.isVip()){
            this.vip=1;
        }
        else{
            this.vip=0;
        }
    }

    public int getVip(){
        return this.vip;
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

}
