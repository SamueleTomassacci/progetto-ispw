package it.uniroma2.dicii.ispw.utils.exceptions;

public class LoginException extends Exception{

    public LoginException(){
        super("Login fallito, ricontrollare username o password.");
    }
}
