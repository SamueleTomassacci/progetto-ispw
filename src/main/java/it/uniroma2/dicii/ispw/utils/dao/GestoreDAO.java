package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestoreDAO {
    public GestoreModel getGestoreByUsername(String username) throws SystemException {
        String query = "SELECT * FROM Utenti where username = ?;";
        GestoreModel gestoreModel = null;
        try {
            Connection conn=ConnectionDB.getConnection();
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();

            gestoreModel = new GestoreModel();

            gestoreModel.setUsername(rs.getString("username"));
            gestoreModel.setEmail(rs.getString("pass"));
            gestoreModel.setCodice(rs.getInt("idUser"));
            return gestoreModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
