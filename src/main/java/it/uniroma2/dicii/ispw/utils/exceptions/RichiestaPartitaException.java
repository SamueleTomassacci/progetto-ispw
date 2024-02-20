package it.uniroma2.dicii.ispw.utils.exceptions;

public class RichiestaPartitaException extends Exception{
    public RichiestaPartitaException(){
        super("L'orario selezionato non è più disponibile, selezionane un altro!");
    }

}