package it.uniroma2.dicii.ispw.utils.bean;

public class IdSessioneBean {
    private int idSession;
    public IdSessioneBean(int codice){
        this.idSession=codice;
    }
    public int getId(){
        return this.idSession;
    }
    public void setId(int codice){
        this.idSession=codice;
    }
}
