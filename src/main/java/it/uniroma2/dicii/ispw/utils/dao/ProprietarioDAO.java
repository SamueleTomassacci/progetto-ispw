package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProprietarioDAO {
    public ProprietarioModel getProprietarioByUsername(String username) throws SystemException {
        String query = "SELECT * FROM Utenti where username = ?;";
        ProprietarioModel proprietarioModel = null;
        try {
            Connection conn= ConnectionDB.getConnection();
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            proprietarioModel = new ProprietarioModel();

            proprietarioModel.setUsername(rs.getString("username"));
            proprietarioModel.setEmail(rs.getString("pass"));
            proprietarioModel.setCodice(rs.getInt("idUser"));
            return proprietarioModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
