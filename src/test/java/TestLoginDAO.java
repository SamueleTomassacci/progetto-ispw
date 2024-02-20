import it.uniroma2.dicii.ispw.model.CredentialsModel;
import it.uniroma2.dicii.ispw.utils.bean.Role;
import it.uniroma2.dicii.ispw.utils.dao.LoginDAO;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
  Test di Samuele Tomassacci
 */

 class TestLoginDAO {

    @Test
    void testcheckIfExists() throws SystemException {
        CredentialsModel credentialsModel=new CredentialsModel("Luca","1234", Role.PROPRIETARIO);
        LoginDAO loginDAO=new LoginDAO();

        assertTrue(loginDAO.checkIfExists(credentialsModel));
    }

}
