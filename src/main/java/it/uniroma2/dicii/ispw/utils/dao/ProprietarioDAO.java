package it.uniroma2.dicii.ispw.utils.dao;


import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProprietarioDAO {
    public ProprietarioModel getProprietarioByUsername(String username) throws SystemException{
        String query = "SELECT * FROM Utenti where username = ?;";
        ProprietarioModel proprietarioModel = null;
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query);) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            proprietarioModel = new ProprietarioModel(rs.getString("username"),rs.getString("email"),rs.getInt("idUser"),rs.getString("nome"),rs.getString("cognome"),rs.getInt("vip"));

            return proprietarioModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

    public void setVip(String username)throws SystemException{
        String query = "UPDATE Utenti SET vip=1 WHERE username = ?;";
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query);) {

            ps.setString(1, username);
            ps.executeUpdate();

        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }

    }
}
