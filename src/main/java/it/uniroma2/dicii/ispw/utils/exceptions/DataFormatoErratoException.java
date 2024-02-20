package it.uniroma2.dicii.ispw.utils.exceptions;

public class DataFormatoErratoException extends Exception{
    public DataFormatoErratoException() {
        super("Data non valida, sarà inserita la data odierna");
    }
}
