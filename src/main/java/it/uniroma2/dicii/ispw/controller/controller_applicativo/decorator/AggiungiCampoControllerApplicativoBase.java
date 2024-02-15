package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.dao.CampoDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

public class AggiungiCampoControllerApplicativoBase extends AggiungiCampoControllerApplicativo {
    @Override
    public void inviaRichiestaGestore(CampoBean richiesta, ProprietarioBean proprietario) {

        CampoDAO campoDao=new CampoDAO();
        CampoModel richiestaModel=new CampoModel(richiesta);
        ProprietarioModel proprietarioModel=new ProprietarioModel(proprietario);
        try{
            campoDao.insertRichiestaCampo(richiestaModel,proprietarioModel);
        }catch(SystemException e){
            //e.printStackTrace();
        }
    }
    public void accetta() {
        //da fare

    }
    public void rifiuta(){
        //da fare

    }


}
