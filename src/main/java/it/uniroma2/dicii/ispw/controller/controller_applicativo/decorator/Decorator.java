package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;

public abstract class Decorator extends aggiungiCampoControllerApplicativo{
    private aggiungiCampoControllerApplicativo component;

    public Decorator(aggiungiCampoControllerApplicativo component){

        this.component = component;
    }

    @Override
    public void inviaRichiestaGestore(CampoBean request, ProprietarioBean proprietario) {

        this.component.inviaRichiestaGestore(request,proprietario);
    }
}
