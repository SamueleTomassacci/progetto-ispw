package it.uniroma2.dicii.ispw.utils.bean;

import it.uniroma2.dicii.ispw.model.GestoreModel;

public class UserBean {
    private String username;
    private String email;

    public UserBean(String username,String email){
        this.username=username;
        this.email=email;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
