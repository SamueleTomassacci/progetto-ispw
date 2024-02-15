package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestoreDAO {
    public GestoreModel getGestoreByUsername(String username) throws SystemException, SQLException {
        String query = "SELECT * FROM Utenti where username = ?;";
        GestoreModel gestoreModel = null;
        Connection conn=ConnectionDB.getConnection();

        try(PreparedStatement ps= conn.prepareStatement(query);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            gestoreModel = new GestoreModel(rs.getString("username"),rs.getString("pass"),rs.getInt("idUser"));


            return gestoreModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
