package it.uniroma2.dicii.ispw.model;

import it.uniroma2.dicii.ispw.utils.bean.CredentialsBean;
import it.uniroma2.dicii.ispw.utils.bean.Role;

public class CredentialsModel {
    private final String username;
    private final String password;
    private int idSession;
    private Role role = null;

    public CredentialsModel(CredentialsBean credbean) {
        this.username = credbean.getUsername();
        this.password = credbean.getPassword();
        this.role=credbean.getRole();
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public Role getRole(){
        return role;
    }
}
