package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.GestoreModel;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<GestoreModel> getAllGestori() throws SystemException {
        String query = "SELECT * FROM Utenti WHERE tipo = 'gestore';";
        List<GestoreModel> lista = new ArrayList<>();
        GestoreModel gestoreModel = null;
        Connection conn = ConnectionDB.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gestoreModel = new GestoreModel();
                gestoreModel.cambiaEmail(rs.getString("email"));
                lista.add(gestoreModel);
            }
            return lista;

        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }




}
