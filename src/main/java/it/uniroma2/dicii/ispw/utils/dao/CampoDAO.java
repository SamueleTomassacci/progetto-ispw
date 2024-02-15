package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CampoDAO {

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
}
