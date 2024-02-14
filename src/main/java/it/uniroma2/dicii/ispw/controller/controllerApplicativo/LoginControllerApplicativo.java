package it.uniroma2.dicii.ispw.controller.controllerApplicativo;

import it.uniroma2.dicii.ispw.model.CredentialsModel;
import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.model.GiocatoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.dao.GestoreDAO;
import it.uniroma2.dicii.ispw.utils.dao.GiocatoreDAO;
import it.uniroma2.dicii.ispw.utils.dao.LoginDAO;
import it.uniroma2.dicii.ispw.utils.dao.ProprietarioDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.SQLException;

public class LoginControllerApplicativo {

    public void login(CredentialsBean cred) throws SystemException, SQLException {
        // Controllo attraverso loginDao se esiste un username con quelle credenziali
        LoginDAO loginDao = new LoginDAO();
        if (cred.getRole() == Role.GESTORE) {
            GestoreModel gestore = null;
            CredentialsModel credentialsModel = new CredentialsModel(cred);
            if (loginDao.checkIfExists(credentialsModel)) {
                GestoreDAO gestoreDAO = new GestoreDAO();
                gestore = gestoreDAO.getGestoreByUsername(cred.getUsername());
                cred.setIdSession(gestore.getCode());
                GestoreBean gestoreBean = new GestoreBean(gestore);
                SessionManager manager = SessionManager.getSessionManager();
                IdSessioneBean id=new IdSessioneBean(gestore.getCode());
                Session sessione = manager.createSession(null, null, gestoreBean, Role.GESTORE, id);
                manager.aggiungiSessione(sessione);
            } else {
                //Trigger o non lo so, in generale eccezione per dire che l'utente non esiste
            }
        }
        else if (cred.getRole() == Role.PROPRIETARIO) {
            ProprietarioModel proprietario = null;
            CredentialsModel credentialsModel = new CredentialsModel(cred);
            if(loginDao.checkIfExists(credentialsModel)){
                ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
                proprietario = proprietarioDAO.getProprietarioByUsername(cred.getUsername());
                cred.setIdSession(proprietario.getCode());
                ProprietarioBean proprietarioBean = new ProprietarioBean(proprietario);
                SessionManager manager = SessionManager.getSessionManager();
                IdSessioneBean id=new IdSessioneBean(proprietario.getCode());
                Session sessione = manager.createSession(null,proprietarioBean,null,Role.PROPRIETARIO,id);
                manager.aggiungiSessione(sessione);
            }
            else{
                //Trigger o non lo so, in generale eccezione per dire che l'utente non esiste
            }
        }
        else if (cred.getRole() == Role.GIOCATORE){
            GiocatoreModel giocatore = null;
            CredentialsModel credentialsModel = new CredentialsModel(cred);
            if(loginDao.checkIfExists(credentialsModel)){
                GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
                giocatore = giocatoreDAO.getGiocatoreByUsername(cred.getUsername());
                cred.setIdSession(giocatore.getCode());
                GiocatoreBean giocatoreBean = new GiocatoreBean(giocatore);
                SessionManager manager = SessionManager.getSessionManager();
                IdSessioneBean id=new IdSessioneBean(giocatore.getCode());
                Session sessione = manager.createSession(giocatoreBean,null,null,Role.PROPRIETARIO,id);
                manager.aggiungiSessione(sessione);
            }
            else{
                //Trigger o non lo so, in generale eccezione per dire che l'utente non esiste
            }
        }


    }
}
