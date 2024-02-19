package it.uniroma2.dicii.ispw.controller.controller_applicativo.CreaPartita;


import it.uniroma2.dicii.ispw.controller.controller_grafico.interfaccia1.giocatore.ListaPartiteObserver;
import it.uniroma2.dicii.ispw.model.partita.*;
import it.uniroma2.dicii.ispw.utils.bean.*;
import it.uniroma2.dicii.ispw.utils.dao.CampoDAO;
import it.uniroma2.dicii.ispw.utils.dao.PartitaDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CreaPartitaControllerApplicativo {
    private ListaPartiteModel listaPartite = new ListaPartiteModel();
    public void inviaRichiesta(RichiestaPartitaBean richiestaPartitaBean) throws SystemException {
        // creiamo model RichiestaPartitaModel
        PartitaModel richiesta = new PartitaModel(richiestaPartitaBean);
        // inviamo la richiesta al db
        PartitaDAO partitaDAO = new PartitaDAO();
        partitaDAO.inviaRichiesta(richiesta);
        // aggiungiamo richiesta alla lista
        listaPartite.addRichiestaPartita(richiesta);
    }

    public ListaNomeCampoBean inizializzasceltaCampo() throws SystemException {
        // prende tutti i campi dal DAO ListaCampiDAO
        CampoDAO campoDAO = new CampoDAO();
        List<PartitaModel> lista = campoDAO.getNomeCampo();
        // trasformazione in un ListaNomeCampoBean da inviare al controller grafico
        ListaNomeCampoBean listaBean = new ListaNomeCampoBean();
        PartitaCampoBean campoBean = null;
        for(PartitaModel campo:lista){
            campoBean = new PartitaCampoBean(campo);
            listaBean.add(campoBean);
        }
        return listaBean;
    }

    public List<LocalTime> inizializzasceltaOrario(PartitaCampoDataBean bean) throws SystemException {
        // creiamo un model con i dati ricevuti a parametro
        PartitaModel richiestaOrari = new PartitaModel(bean.getNome(), bean.getIndirizzo(), bean.getGiorno());
        // prende orario di apertura
        CampoDAO campoDAO = new CampoDAO();
        LocalTime apertura = campoDAO.getOrarioApertura(richiestaOrari);
        // prende orario di chiusura
        LocalTime chiusura = campoDAO.getOrarioChiusura(richiestaOrari);
        // crea lista di orari possibili
        if(apertura.getMinute() != 0){
            apertura = apertura.plusMinutes( (long)(60)-apertura.getMinute());
        }
        List<LocalTime> orariPossibili = new ArrayList<>();
        // popolazione degli orari ogni ora
        for (LocalTime orario = apertura; orario.isBefore(chiusura);) {
            orariPossibili.add(orario);
            // Incrementa di un'ora
            orario = orario.plusHours(1);
        }
        // prende lista orari occupati
        PartitaDAO partitaDAO = new PartitaDAO();
        List<LocalTime> orariOccupati = partitaDAO.getOrariOccupati(richiestaOrari);
        // sottrae agli orari possibili
        orariPossibili.removeAll(orariOccupati);
        return orariPossibili;
    }

    public void aggiornaRichieste() throws SystemException {
        PartitaDAO partitaDAO = new PartitaDAO();
        List<PartitaModel> lista = listaPartite.recuperaLista();
        for (PartitaModel partita : lista){
            if(partita.infoStato() == statoPartita.Pendente){
                partita.impostaStato(partitaDAO.aggiornaStatoPartita(partita));
                if(partita.infoStato() != statoPartita.Pendente){
                    // aggiornamento di questa partita negli osservatori
                    listaPartite.notifyObservers(partita);
                }
            }
        }
    }

    public void inizializzaPartite(UserBean user, ListaPartiteObserver observer) throws SystemException {
        listaPartite.register(observer);
        PartitaDAO partitaDAO = new PartitaDAO();
        List<PartitaModel> lista = partitaDAO.getPartiteCreatedByUsername(user.getUsername());
        for(PartitaModel partita : lista){
            listaPartite.addRichiestaPartita(partita);
        }
    }

    public List<PartitaBean> inizializzaRichiesteProprietario(UserBean user) throws SystemException {
        PartitaDAO partitaDAO = new PartitaDAO();
        List<PartitaModel> lista = partitaDAO.getPartiteforProprietario(user.getUsername());
        // trasformiamo i model in Bean per passarli al controller grafico
        List<PartitaBean> listaBean = new ArrayList<>();
        for (PartitaModel partita : lista){
            PartitaBean bean = new PartitaBean(partita);
            listaBean.add(bean);
        }
        return listaBean;
    }

    public void rispondiRichiesta(PartitaBean partitaBean) throws SystemException {
        // trasformiamo il bean in un model
        PartitaModel partitaModel = new PartitaModel(partitaBean);
        // prendiamo un istanza del partitaDAO
        PartitaDAO partitaDAO = new PartitaDAO();
        partitaDAO.inviaRisposta(partitaModel);
    }
}
