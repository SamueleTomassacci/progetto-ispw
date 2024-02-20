package it.uniroma2.dicii.ispw.utils.exceptions;


public class DataMancanteException extends Exception{

    public DataMancanteException(){
        super("Non avendo selezionato una data gli orari disponibili si riferiranno alla data odierna");
    }

}
