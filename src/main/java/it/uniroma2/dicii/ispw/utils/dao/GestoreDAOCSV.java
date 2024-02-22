package it.uniroma2.dicii.ispw.utils.dao;

import com.opencsv.exceptions.CsvValidationException;
import it.uniroma2.dicii.ispw.model.GestoreModel;


import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class GestoreDAOCSV {
    private static final String CSV_GESTORE = "csv/gestore.csv";
    private File fd;

    private static int indiceUserId=0;
    private static int indiceUsername=1;
    private static int indiceEmail=5;



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

    public GestoreModel getGestoreByUsername(String username) throws SystemException {
        GestoreModel gestore = null;
        try( CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            String[] value;


            while ((value = csvReader.readNext()) != null) {



                if (value[GestoreDAOCSV.indiceUsername].equals(username)) {
                    gestore = new GestoreModel(value[GestoreDAOCSV.indiceUsername], value[GestoreDAOCSV.indiceEmail], Integer.parseInt(value[GestoreDAOCSV.indiceUserId]));

                }
            }

            return gestore;

        } catch (IOException | CsvValidationException exc) {
            SystemException exception = new SystemException();
            exception.initCause(exc);
            throw exception;
        }

    }

    public List<GestoreModel> getAllGestori() throws SystemException {
       List<GestoreModel> lista=new ArrayList<>();
       GestoreModel gestore = null;

        try( CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            String[] val;


            while ((val = csvReader.readNext()) != null) {

               gestore=new GestoreModel();
               gestore.cambiaEmail(val[GestoreDAOCSV.indiceEmail]);
               lista.add(gestore);

            }

            return lista;

        } catch (IOException | CsvValidationException exc) {
            SystemException exception = new SystemException();
            exception.initCause(exc);
            throw exception;
        }

    }
}
