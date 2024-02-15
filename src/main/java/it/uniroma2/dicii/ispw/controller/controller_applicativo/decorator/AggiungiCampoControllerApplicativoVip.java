package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;

public class AggiungiCampoControllerApplicativoVip extends Decorator{
    public AggiungiCampoControllerApplicativoVip(AggiungiCampoControllerApplicativo component) {
        super(component);
    }
    private void decorazioneAggiunta(CampoBean richiesta){
        //da fare

    }
    @Override
    public void inviaRichiestaGestore(CampoBean request, ProprietarioBean proprietario) {
        super.inviaRichiestaGestore(request, proprietario);
        this.decorazioneAggiunta(request);
    }

}
