package it.uniroma2.dicii.ispw.utils.factory;

import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;


public class Factory {

    public DialogBox getBox(Exception e){
        if(e instanceof SystemException){
            return new Error();
        }
        else{
            return new Notification();
        }
    }
}
