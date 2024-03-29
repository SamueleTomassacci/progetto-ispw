package it.uniroma2.dicii.ispw.utils.dao;

import com.opencsv.exceptions.CsvValidationException;

import it.uniroma2.dicii.ispw.model.ProprietarioModel;

import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import com.opencsv.CSVReader;

public class ProprietarioDAOCSV {
    private static final String CSV_GESTORE = "csv/proprietario.csv";
    private File fd;
    private static int indexUserId=0;
    private static int indexUsername=1;
    private static int indexEmail=2;
    private static int indexNome=3;
    private static int indexCognome=4;
    private static int indexVip=5;



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

                int pos =ProprietarioDAOCSV.indexUsername;

                if (rec[pos].equals(username)) {
                    proprietario = new ProprietarioModel(rec[ProprietarioDAOCSV.indexUsername], rec[ProprietarioDAOCSV.indexEmail], Integer.parseInt(rec[ProprietarioDAOCSV.indexUserId]), rec[ProprietarioDAOCSV.indexNome], rec[ProprietarioDAOCSV.indexCognome], Integer.parseInt(rec[ProprietarioDAOCSV.indexVip]));
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

            String[] recValue;
            StringBuilder updatedCSVContent = new StringBuilder();
            boolean found = false;

            while ((recValue = csvReader.readNext()) != null) {
                int pos = ProprietarioDAOCSV.indexUsername;

                if (recValue[pos].equals(username)) {
                    found = true;
                    String updatedRecord = recValue[ProprietarioDAOCSV.indexUserId] + ","
                            + recValue[ProprietarioDAOCSV.indexUsername] + ","
                            + recValue[ProprietarioDAOCSV.indexEmail] + ","
                            + recValue[ProprietarioDAOCSV.indexNome] + ","
                            + recValue[ProprietarioDAOCSV.indexCognome] + ","
                            + "1"; // Imposta il VIP a 1

                    updatedCSVContent.append(updatedRecord).append("\n");
                } else {
                    updatedCSVContent.append(String.join(",", recValue)).append("\n");
                }
            }

            if (!found) {
                throw  new SystemException("Username non trovato nel CSV");

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
