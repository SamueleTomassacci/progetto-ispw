package it.uniroma2.dicii.ispw.controller.controller_applicativo;

import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.dao.ProprietarioDAO;
import it.uniroma2.dicii.ispw.utils.dao.ProprietarioDAOCSV;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;





public class VipControllerApplicativo {
    public void upgradeVip(ProprietarioBean proprietario) throws SystemException {

       // ProprietarioDAOCSV proprietarioDAO=new ProprietarioDAOCSV();
        ProprietarioDAO proprietarioDAO=new ProprietarioDAO();

        proprietarioDAO.setVip(proprietario.getUsername());

    }
}
