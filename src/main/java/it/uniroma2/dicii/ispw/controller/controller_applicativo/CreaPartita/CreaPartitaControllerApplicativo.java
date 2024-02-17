package it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita;


import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.NomeCampoModel;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.ListaNomeCampoBean;
import it.uniroma2.dicii.ispw.utils.bean.NomeCampoBean;
import it.uniroma2.dicii.ispw.utils.bean.RichiestaPartitaBean;
import it.uniroma2.dicii.ispw.utils.dao.CampoDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.util.ArrayList;
import java.util.List;

public class CreaPartitaControllerApplicativo {
    public void inviaRichiestaGestore(RichiestaPartitaBean richiestaPartitaBean){

    }

    public ListaNomeCampoBean inizializzasceltaCampo() throws SystemException {
        // prende tutti i campi dal DAO ListaCampiDAO
        CampoDAO campoDAO = new CampoDAO();
        List<NomeCampoModel> lista = campoDAO.getNomeCampo();
        // trasformazione in un ListaNomeCampoBean da inviare al controller grafico
        ListaNomeCampoBean listaBean = new ListaNomeCampoBean();
        NomeCampoBean campoBean = null;
        for(NomeCampoModel campo:lista){
            campoBean = new NomeCampoBean(campo);
            listaBean.add(campoBean);
        }
        return listaBean;
    }
}
