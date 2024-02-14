package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.utils.bean.RichiestaCampoBean;

public abstract class Decorator extends aggiungiCampoControllerApplicativo{
    private aggiungiCampoControllerApplicativo component;

    public Decorator(aggiungiCampoControllerApplicativo component){

        this.component = component;
    }

    @Override
    public void inviaRichiestaGestore(RichiestaCampoBean request) {

        this.component.inviaRichiestaGestore(request);
    }
}
