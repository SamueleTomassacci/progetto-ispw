package it.uniroma2.dicii.ispw.utils.exceptions;

public class SystemException extends Exception{

    public SystemException() {
        super("Si è presentato un errore tecnico, siamo spiacenti.");
    }

    public SystemException(String message) {
        super("A technical error occurred.\n" + message);
    }
}