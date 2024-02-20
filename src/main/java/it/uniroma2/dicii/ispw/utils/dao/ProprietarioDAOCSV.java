package it.uniroma2.dicii.ispw.utils.dao;

import com.opencsv.exceptions.CsvValidationException;
import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.opencsv.CSVReader;

public class ProprietarioDAOCSV {
    private static final String CSV_GESTORE = "csv/proprietario.csv";
    private File fd;

    private static class ProprietarioAttributesOrder {
        public static int getIndex_UserId() {
            return 0;
        }

        public static int getIndex_Username() {
            return 1;
        }

        public static int getIndex_Email() {
            return 2;
        }

        public static int getIndex_Nome() {

            return 3;
        }

        public static int getIndex_Cognome() {

            return 4;
        }

        public static int getIndex_Vip() {

            return 5;
        }

    }

    public ProprietarioDAOCSV() throws SystemException {
        this.fd = new File(CSV_GESTORE);
        try {

            if (!fd.exists()) {
               boolean created= fd.createNewFile();
                if(!created){
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }

    }

    public ProprietarioModel getProprietarioByUsername(String username) throws SystemException {
        ProprietarioModel proprietario = null;
        try( CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            String[] rec;


            while ((rec = csvReader.readNext()) != null) {

                int pos = ProprietarioAttributesOrder.getIndex_Username();

                if (rec[pos].equals(username)) {
                    proprietario = new ProprietarioModel(rec[ProprietarioAttributesOrder.getIndex_Username()], rec[ProprietarioAttributesOrder.getIndex_Email()], Integer.parseInt(rec[ProprietarioAttributesOrder.getIndex_UserId()]), rec[ProprietarioAttributesOrder.getIndex_Nome()], rec[ProprietarioAttributesOrder.getIndex_Cognome()], Integer.parseInt(rec[ProprietarioAttributesOrder.getIndex_Vip()]));
                }
            }
            return proprietario;

        } catch (IOException | CsvValidationException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

    public void setVip(String username) throws SystemException {
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            String[] record;
            StringBuilder updatedCSVContent = new StringBuilder();
            boolean found = false;

            while ((record = csvReader.readNext()) != null) {
                int pos = ProprietarioAttributesOrder.getIndex_Username();

                if (record[pos].equals(username)) {
                    found = true;
                    String updatedRecord = record[ProprietarioAttributesOrder.getIndex_UserId()] + ","
                            + record[ProprietarioAttributesOrder.getIndex_Username()] + ","
                            + record[ProprietarioAttributesOrder.getIndex_Email()] + ","
                            + record[ProprietarioAttributesOrder.getIndex_Nome()] + ","
                            + record[ProprietarioAttributesOrder.getIndex_Cognome()] + ","
                            + "1"; // Imposta il VIP a 1

                    updatedCSVContent.append(updatedRecord).append("\n");
                } else {
                    updatedCSVContent.append(String.join(",", record)).append("\n");
                }
            }

            if (!found) {
                SystemException exception = new SystemException("Username non trovato nel CSV");
                throw exception;
            }

            // Sovrascrive il file CSV con i dati aggiornati
            Files.write(fd.toPath(), updatedCSVContent.toString().getBytes());

        } catch (IOException | CsvValidationException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

}
