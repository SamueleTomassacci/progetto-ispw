package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ListaRichiesteCampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.dao.CampoDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.util.ArrayList;
import java.util.List;

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

    public List<CampoBean> caricaRichieste() throws SystemException {
        CampoDAO campoDAO=new CampoDAO();
        List<CampoModel> lista=campoDAO.getRichiesteCampo();
        List<CampoBean> listaBean=new ArrayList<>();
        CampoBean campoBean=null;
        for(CampoModel campo:lista){
            campoBean=new CampoBean(campo);
            listaBean.add(campoBean);
        }
        return listaBean;


    }
    public void accetta(CampoBean campo) throws SystemException {
        CampoDAO campoDAO=new CampoDAO();
        CampoModel campoModel=new CampoModel(campo);
        ProprietarioModel proprietarioModel=campoDAO.getProprietarioFromRichiestaCampo(campoModel);
        campoDAO.eliminaRichiesta(campoModel);
        campoDAO.aggiungiCampo(campoModel,proprietarioModel);
    }
    public void rifiuta(CampoBean campo) throws SystemException {
       CampoDAO campoDAO= new CampoDAO();
       CampoModel campoModel=new CampoModel(campo);
       campoDAO.eliminaRichiesta(campoModel);

    }


}
