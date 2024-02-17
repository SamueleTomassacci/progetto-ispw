package it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1;

import it.uniroma2.dicii.ispw.utils.bean.IdSessioneBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.FotoBean;
import it.uniroma2.dicii.ispw.utils.bean.interfaccia1.CampoSenzaFotoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.IOException;

public abstract class ControllerGrafico {
    public abstract void inizializza(IdSessioneBean id, CampoSenzaFotoBean campoSenzaFoto, FotoBean foto) throws SystemException;
}
/*Uso del polimorfismo per istanziare i vari controller grafici, in particolare tutti i controller grafici
  che devono essere aggiornati in base all'utente estendono tale classe implementando il metodo inizializza
 */