package it.uniroma2.dicii.ispw.utils.bean;


public class CredentialsBean {
    private String username;
    private String password;
    private int idSession;

    private Role role = null;

    public CredentialsBean(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public CredentialsBean(){
    }
    public void setRole(Role role){
        this.role=role;
    }
    public Role getRole() {
        return this.role;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setIdSession(int idSession){
        this.idSession = idSession;
    }
    public int getIdSession(){
        return this.idSession;
    }
    public void setUsername(String nome){
        this.username=nome;
    }
    public void setPassword(String password){
        this.password=password;
    }
}