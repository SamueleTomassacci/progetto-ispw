package it.uniroma2.dicii.ispw.utils.exceptions;

public class TariffaException extends Exception{
   public TariffaException() {
       super("La tariffa deve essere un numero intero.");
   }
}
