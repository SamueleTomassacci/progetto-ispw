package it.uniroma2.dicii.ispw.utils.exceptions;

public class CampoEsistenteException extends Exception{
    public CampoEsistenteException(){
        super("Messaggio standard");
    }
    public CampoEsistenteException(String message){
        super(message);
    }
}
