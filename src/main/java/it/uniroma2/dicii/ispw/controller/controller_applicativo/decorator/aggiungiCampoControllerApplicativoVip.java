package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;

public class aggiungiCampoControllerApplicativoVip extends Decorator{
    public aggiungiCampoControllerApplicativoVip(aggiungiCampoControllerApplicativo component) {
        super(component);
    }
    private void decorazioneAggiunta(CampoBean richiesta){

    }
    @Override
    public void inviaRichiestaGestore(CampoBean request, ProprietarioBean proprietario) {
        super.inviaRichiestaGestore(request, proprietario);
        this.decorazioneAggiunta(request);
    }

}
