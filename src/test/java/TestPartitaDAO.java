
import it.uniroma2.dicii.ispw.model.partita.PartitaModel;
import it.uniroma2.dicii.ispw.utils.dao.PartitaDAO;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Il testGetOrarioOccupati individua per il campo "Centro Sportivo Rossi2" in via "Via Rossi 23" in data "27/02/2023" se sono presenti degli orari già prenotati.
    Il test fallisce se non è esattamente presente un singolo orario prenotato che è alle "13:00"

    Flavio Simonelli
 */
 class TestPartitaDAO {
    @Test
    void testGetOrariOccupati() throws SystemException {
        LocalTime orario = LocalTime.parse("13:00");
        LocalDate data = LocalDate.of(2023, 2, 27);
        PartitaModel partitaModel = new PartitaModel("Centro Sportivo Rossi2", "Via Rossi 23", data);
        PartitaDAO partitaDAO = new PartitaDAO();
        List<LocalTime> orariOccupati = partitaDAO.getOrariOccupati(partitaModel);
        boolean validate = true;
        for (LocalTime ora : orariOccupati) {
            if (!ora.equals(orario)) {
                validate = false;
                break;
            }
        }
        assertTrue(validate);
    }
}