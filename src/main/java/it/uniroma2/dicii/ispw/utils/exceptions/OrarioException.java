package it.uniroma2.dicii.ispw.utils.exceptions;

public class OrarioException extends Exception{
    public OrarioException(){
        super("Il formato dell'orario deve essere hh:mm");

    }
}
