package it.uniroma2.dicii.ispw.utils.exceptions;

import it.uniroma2.dicii.ispw.utils.factory.DialogBox;
import it.uniroma2.dicii.ispw.utils.factory.Factory;

public class GestoreEccezioni {
    private GestoreEccezioni() {
        // ignored
    }

    private static GestoreEccezioni instance = null;
    private Factory factory = new Factory();

    public static GestoreEccezioni getInstance() {
        if (instance == null) {
            instance = new GestoreEccezioni();
        }
        return instance;
    }

    public void handleException(Exception e) {
        DialogBox myDialogBox = factory.getBox(e);
        myDialogBox.showBox(e.getMessage());
    }
}
