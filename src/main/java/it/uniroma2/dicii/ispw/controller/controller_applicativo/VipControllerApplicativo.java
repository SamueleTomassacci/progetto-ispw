package it.uniroma2.dicii.ispw.controller.controller_applicativo;

import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.dao.ProprietarioDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.SQLException;

/*
  Gestisci con le eccezioni il fatto che il proprietario potrebbe già essere vip
 */

public class VipControllerApplicativo {
    public void upgradeVip(ProprietarioBean proprietario) throws SQLException, SystemException {
        ProprietarioDAO proprietarioDAO=new ProprietarioDAO();
        proprietarioDAO.setVip(proprietario.getUsername());

    }
}
