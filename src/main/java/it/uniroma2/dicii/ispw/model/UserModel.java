package it.uniroma2.dicii.ispw.model;

public class UserModel{
    private String username;
    private String email;
    private int codice;

    public UserModel(String username, String email, int codice){
        this.username=username;
        this.email=email;
        this.codice=codice;
    }
    public UserModel(String username, String email){
        this.username=username;
        this.email=email;

    }
    public UserModel(String username){
        this.username=username;
    }



    public UserModel() {

    }

    public int getCode(){
        return this.codice;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    public void cambiaUsername(String username) { this.username = username;}
    public void cambiaEmail(String email) { this.email = email;}
    public void setCodice(int codice) { this.codice = codice;}



}
