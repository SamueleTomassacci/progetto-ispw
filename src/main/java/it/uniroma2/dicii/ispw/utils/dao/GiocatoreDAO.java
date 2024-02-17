package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.GiocatoreModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GiocatoreDAO {
    public GiocatoreModel getGiocatoreByUsername(String username) throws SystemException{
        String query = "SELECT username,idUser,altezza,eta,ruoloBasket FROM Utenti where username = ?;";
        GiocatoreModel giocatoreModel = null;
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            giocatoreModel = new GiocatoreModel(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
            return giocatoreModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
