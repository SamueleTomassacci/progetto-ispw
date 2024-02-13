package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.CredentialsModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {


    public boolean checkIfExists(CredentialsModel credentialsModel) throws SystemException {
        String query = "SELECT * FROM Utenti WHERE username = ? AND pass = ? AND tipo = ?";

        try {
            Connection conn=ConnectionDB.getConnection();
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1, credentialsModel.getUsername());
            ps.setString(2, credentialsModel.getPassword());
            ps.setString(3, credentialsModel.getRole().name());

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
