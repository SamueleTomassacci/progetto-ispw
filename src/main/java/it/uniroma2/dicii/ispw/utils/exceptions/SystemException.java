package it.uniroma2.dicii.ispw.utils.exceptions;

public class SystemException extends Exception{

    public SystemException() {
        super("A technical error occurred.");
    }

    public SystemException(String message) {
        super("A technical error occurred.\n" + message);
    }
}