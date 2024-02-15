package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

public abstract class AggiungiCampoControllerApplicativo {
    public abstract void inviaRichiestaGestore(CampoBean richiesta, ProprietarioBean proprietario) throws SystemException;
}
