package it.uniroma2.dicii.ispw.utils.dao;

import com.opencsv.exceptions.CsvValidationException;
import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.LoginException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.opencsv.CSVReader;

public class GestoreDAOCSV {
    private static final String CSV_GESTORE = "csv/gestore.csv";
    private File fd;

    private static class GestoreAttributesOrder {
        public static int getIndice_UserId() {
            return 0;
        }

        public static int getIndice_Username() {
            return 1;
        }

        public static int getIndice_Email() {
            return 2;
        }
    }

    public GestoreDAOCSV() throws SystemException {
        this.fd = new File(CSV_GESTORE);
        try {

            if (!fd.exists()) {
                boolean bool= fd.createNewFile();
                if(!bool){
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }

    }

    public GestoreModel getGestoreByUsername(String username) throws SystemException, LoginException {
        GestoreModel gestore = null;
        try( CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            String[] record;


            while ((record = csvReader.readNext()) != null) {

                int pos = GestoreAttributesOrder.getIndice_Username();

                if (record[pos].equals(username)) {
                    gestore = new GestoreModel(record[GestoreAttributesOrder.getIndice_Username()], record[GestoreAttributesOrder.getIndice_Email()], Integer.parseInt(record[GestoreAttributesOrder.getIndice_UserId()]));

                }
            }

            return gestore;

        } catch (IOException | CsvValidationException exc) {
            SystemException exception = new SystemException();
            exception.initCause(exc);
            throw exception;
        }

    }
}
