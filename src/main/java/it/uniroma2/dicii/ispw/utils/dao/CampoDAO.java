package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.bean.ConverterBean;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.engineering.ConverterToFileEngineering;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampoDAO {
    private static final String PATHCAMPIIMG = "campi_img/";


    public List<CampoModel> getRichiesteCampo() throws SystemException {
        String query="SELECT * FROM richiestacampo;";
        Connection conn= ConnectionDB.getConnection();
        List<CampoModel> lista=new ArrayList<>();
        CampoModel campo=null;
        try(PreparedStatement ps= conn.prepareStatement(query)){

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InputStream inputStream = (rs.getBinaryStream(6));
                String filePath = PATHCAMPIIMG + rs.getString(1) + "pic" + ".png";
                File file = new File(filePath);
                ConverterToFileEngineering converterToFile = new ConverterToFileEngineering();
                converterToFile.fromInputStreamToFile(new ConverterBean(inputStream, file));
                campo = new CampoModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getTime(4),rs.getTime(5),rs.getString(8),file);
                lista.add(campo);
            }
            return lista;

        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }



    }

    public void insertRichiestaCampo(CampoModel campo, ProprietarioModel proprietario) throws SystemException{
        String query="INSERT INTO richiestacampo VALUES(?,?,?,?,?,?,?,?);";
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());
            ps.setInt(3,campo.costoOrario());
            ps.setTime(4,campo.inizioAttivita());
            ps.setTime(5,campo.fineAttivita());
            ps.setBlob(6, new FileInputStream(campo.recuperaImmagine()));
            ps.setString(7,proprietario.getUsername());
            ps.setString(8,campo.credPagamento());

            int righeModificate = ps.executeUpdate();


        }catch(SQLException | FileNotFoundException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

    public void eliminaRichiesta(CampoModel campo) throws SystemException {
        String query="DELETE FROM richiestacampo WHERE nome= ? and indirizzo = ?;";
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());

            int righeModificate = ps.executeUpdate();


        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

    public ProprietarioModel getProprietarioFromRichiestaCampo(CampoModel campo) throws SystemException {
        String query="SELECT proprietario, iban FROM richiestacampo WHERE nome= ? and indirizzo =?;";
        Connection conn= ConnectionDB.getConnection();
        ProprietarioModel proprietario=null;
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());

            ResultSet rs = ps.executeQuery();
            rs.next();
            proprietario=new ProprietarioModel(rs.getString(1),rs.getString(2));

        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        return proprietario;
    }

    public void aggiungiCampo(CampoModel campo, ProprietarioModel proprietario) throws SystemException {
        String query="INSERT INTO campo VALUES(?,?,?,?,?,?,?,?);";
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());
            ps.setInt(3,campo.costoOrario());
            ps.setTime(4,campo.inizioAttivita());
            ps.setTime(5,campo.fineAttivita());
            ps.setTime(4,campo.inizioAttivita());
            ps.setBlob(6, new FileInputStream(campo.recuperaImmagine()));
            ps.setString(7,proprietario.getUsername());
            ps.setString(8,proprietario.credBancarie());

            ps.executeUpdate();


        }catch(SQLException | FileNotFoundException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }

    }
}





