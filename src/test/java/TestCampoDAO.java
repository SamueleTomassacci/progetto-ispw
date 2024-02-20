import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.dao.CampoDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
/*
  Test di Samuele Tomassacci
 */
public class TestCampoDAO {

    @Test
    void testGetProprietarioFromRichiestaCampo() throws SystemException{
        CampoDAO campoDAO=new CampoDAO();
        CampoModel campoModel=new CampoModel("Centro Sportivo Rossi2","Via Rossi 23",8,null,null,null,null);
        ProprietarioModel proprietarioModel=campoDAO.getProprietarioFromRichiestaCampo(campoModel);

        String username=proprietarioModel.getUsername();

        assertTrue(username.equals(("Luca")));
    }
}
