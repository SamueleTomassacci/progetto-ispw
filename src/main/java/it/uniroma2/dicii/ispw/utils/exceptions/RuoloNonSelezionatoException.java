package it.uniroma2.dicii.ispw.utils.exceptions;

public class RuoloNonSelezionatoException extends Exception {
    public RuoloNonSelezionatoException(){
        super("Attenzione, selezionare un ruolo");
    }
}
