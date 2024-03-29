package it.uniroma2.dicii.ispw.controller.controller_applicativo;

import it.uniroma2.dicii.ispw.model.CredentialsModel;
import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.model.GiocatoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.Session;
import it.uniroma2.dicii.ispw.utils.SessionManager;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.dao.*;
import it.uniroma2.dicii.ispw.utils.exceptions.LoginException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;



public class LoginControllerApplicativo {

    public void login(CredentialsBean cred) throws SystemException, LoginException {
        // Controllo attraverso loginDao se esiste un username con quelle credenziali
        LoginDAO loginDao = new LoginDAO();
        if (cred.getRole() == Role.GESTORE) {

            GestoreModel gestore = null;
            CredentialsModel credentialsModel = new CredentialsModel(cred);
            if (loginDao.checkIfExists(credentialsModel)) {

                //Creare il daoCSV per usare il file system



                GestoreDAO gestoreDAO = new GestoreDAO();

                gestore = gestoreDAO.getGestoreByUsername(cred.getUsername());

                cred.setIdSession(gestore.getCode());
                GestoreBean gestoreBean = new GestoreBean(gestore);
                SessionManager manager = SessionManager.getSessionManager();
                IdSessioneBean id=new IdSessioneBean(gestore.getCode());
                Session sessione = manager.createSession(null, null, gestoreBean, id);
                manager.aggiungiSessione(sessione);
            } else {
                throw new LoginException();
            
            }
        }
        else if (cred.getRole() == Role.PROPRIETARIO) {

            ProprietarioModel proprietario = null;
            CredentialsModel credentialsModel = new CredentialsModel(cred);
            if(loginDao.checkIfExists(credentialsModel)){

                //Creare il daoCSV per usare il file system

                ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
                proprietario = proprietarioDAO.getProprietarioByUsername(cred.getUsername());
                cred.setIdSession(proprietario.getCode());
                ProprietarioBean proprietarioBean = new ProprietarioBean(proprietario);
                SessionManager manager = SessionManager.getSessionManager();
                IdSessioneBean id=new IdSessioneBean(proprietario.getCode());
                Session sessione = manager.createSession(null,proprietarioBean,null,id);
                manager.aggiungiSessione(sessione);
            }
            else{
                throw new LoginException();

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
                Session sessione = manager.createSession(giocatoreBean,null,null,id);
                manager.aggiungiSessione(sessione);
            }
            else{
                throw new LoginException();

            }
        }


    }
}
