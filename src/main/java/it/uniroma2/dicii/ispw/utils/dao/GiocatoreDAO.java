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
        String query = "SELECT * FROM Utenti where username = ?;";
        GiocatoreModel giocatoreModel = null;
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            giocatoreModel = new GiocatoreModel(rs.getString("username"),rs.getInt("idUser"),rs.getInt("altezza"),rs.getInt("eta"),rs.getString("ruoloBasket"));


            return giocatoreModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
