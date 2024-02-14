package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.utils.bean.RichiestaCampoBean;

public class aggiungiCampoControllerApplicativoVip extends Decorator{
    public aggiungiCampoControllerApplicativoVip(aggiungiCampoControllerApplicativo component) {
        super(component);
    }
    private void decorazioneAggiunta(RichiestaCampoBean richiesta){

    }
    @Override
    public void inviaRichiestaGestore(RichiestaCampoBean request) {
        super.inviaRichiestaGestore(request);
        this.decorazioneAggiunta(request);
    }

}
