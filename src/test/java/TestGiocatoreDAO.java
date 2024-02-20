import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.GiocatoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.dao.CampoDAO;
import it.uniroma2.dicii.ispw.utils.dao.GiocatoreDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGiocatoreDAO {
    @Test
    public void testGetGiocatoreFromUsername() throws SystemException {
        GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
        String username = "mario";
        GiocatoreModel giocatoreModel=giocatoreDAO.getGiocatoreByUsername(username);
        String username1 = giocatoreModel.getUsername();
        int eta = giocatoreModel.caratteristicaEta();
        int altezza = giocatoreModel.caratteristicaAltezza();
        String ruoloBasket = giocatoreModel.caratteristicaRuoloBasket();
        // Verifica che il nome utente ottenuto dal modello sia uguale a quello atteso
        assertEquals("mario", username1);
        // Verifica che l'età ottenuta dal modello sia uguale a quella attesa
        assertEquals(22, eta);
        // Verifica che l'altezza ottenuta dal modello sia uguale a quella attesa
        assertEquals(198, altezza);
        // Verifica che il ruolo basket ottenuto dal modello sia uguale a quello atteso
        assertEquals("Playmaker", ruoloBasket);
    }
}
