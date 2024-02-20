import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Il test controlla la connessione con il DataBase.
    Ha successo se la connessione è correttamente stabilita, fallisce altrimenti

    Flavio Simonelli
*/

public class TestConnectionDB {
    @Test
    public void testgetConnection() throws SystemException {

        int value = 0;

        if (ConnectionDB.getConnection() != null) {
            value = 1;
        }

        assertEquals(1, value);

    }
}
