package it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator;

import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.bean.EmailBean;
import it.uniroma2.dicii.ispw.utils.bean.ProprietarioBean;
import it.uniroma2.dicii.ispw.utils.dao.GestoreDAO;
import it.uniroma2.dicii.ispw.utils.engineering.EmailEngineering;
import it.uniroma2.dicii.ispw.utils.exceptions.CampoEsistenteException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.util.List;

public class AggiungiCampoControllerApplicativoVip extends Decorator{
    public AggiungiCampoControllerApplicativoVip(AggiungiCampoControllerApplicativo component) {

        super(component);
    }
    private void decorazioneAggiunta() throws SystemException {
        EmailBean email=null;
        EmailEngineering emailEngineering=new EmailEngineering();
        GestoreDAO gestoreDAO=new GestoreDAO();
        List<GestoreModel> lista= gestoreDAO.getAllGestori();
        for(GestoreModel gestore: lista){
            email=new EmailBean();
            email.setSubject("Richiesta aggiunta campo");
            email.setText("Gentile gestore, è stata appena inviata una nuova richiesta di aggiunta campo, la preghiamo di visionarla il prima possibile");
            email.setEmail(gestore.getEmail());
            emailEngineering.mandaEmail(email);
        }


    }
    @Override
    public void inviaRichiestaGestore(CampoBean request, ProprietarioBean proprietario) throws SystemException, CampoEsistenteException {
        super.inviaRichiestaGestore(request, proprietario);
        this.decorazioneAggiunta();
    }

}
