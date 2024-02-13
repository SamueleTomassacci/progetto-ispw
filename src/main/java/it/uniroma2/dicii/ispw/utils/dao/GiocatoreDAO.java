package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.GiocatoreModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GiocatoreDAO {
    public GiocatoreModel getGiocatoreByUsername(String username) throws SystemException {
        String query = "SELECT * FROM Utenti where username = ?;";
        GiocatoreModel giocatoreModel = null;
        try {
            Connection conn= ConnectionDB.getConnection();
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            giocatoreModel = new GiocatoreModel();

            giocatoreModel.setUsername(rs.getString("username"));
            giocatoreModel.setEmail(rs.getString("pass"));
            giocatoreModel.setCodice(rs.getInt("idUser"));
            return giocatoreModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
