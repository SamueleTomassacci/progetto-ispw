package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.partita.PartitaModel;
import it.uniroma2.dicii.ispw.model.partita.statoPartita;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;

import it.uniroma2.dicii.ispw.utils.exceptions.RichiestaPartitaException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PartitaDAO {
    public List<LocalTime> getOrariOccupati(PartitaModel richiesta) throws SystemException {
        String query = "SELECT OraInizio FROM partita where nomeCampo = ? and indirizzocampo = ? and data = ?;";
        Connection conn= ConnectionDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, richiesta.recuperaNome());
            ps.setString(2, richiesta.recuperaIndirizzo());
            ps.setDate(3, Date.valueOf(richiesta.recuperaData()));
            ResultSet rs = ps.executeQuery();
            List<LocalTime> orariOccupati = new ArrayList<>();
            while (rs.next()) {
                Time orario = rs.getTime(1);
                orariOccupati.add(orario.toLocalTime());
            }

            return orariOccupati;

            }catch(SQLException e){
                SystemException exception = new SystemException();
                exception.initCause(e);
                throw exception;
            }
    }

    public void inviaRichiesta(PartitaModel richiesta) throws SystemException, RichiestaPartitaException {
        String query = "INSERT INTO partita values( ? , ? , ? , ? , ? , ? , ? );";
        Connection conn = ConnectionDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1,richiesta.recuperaNome());
            ps.setString(2,richiesta.recuperaIndirizzo());
            ps.setTime(3, Time.valueOf(richiesta.recuperaOrarioInizio()));
            ps.setDate(4, Date.valueOf(richiesta.recuperaData()));
            ps.setString(5, String.valueOf(statoPartita.PENDENTE));
            ps.setString(6,richiesta.recuperaCreatore());
            ps.setInt(7,richiesta.recuperaNumGiocatori());
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new RichiestaPartitaException();
            } else {
                SystemException exception = new SystemException();
                exception.initCause(e);
                throw exception;
            }
        }
    }

    public statoPartita aggiornaStatoPartita(PartitaModel richiesta) throws SystemException {
        String query = "SELECT stato FROM partita where nomeCampo = ? and indirizzocampo = ? and data = ? and OraInizio = ?;";
        Connection conn = ConnectionDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1,richiesta.recuperaNome());
            ps.setString(2,richiesta.recuperaIndirizzo());
            ps.setDate(3, Date.valueOf(richiesta.recuperaData()));
            ps.setTime(4, Time.valueOf(richiesta.recuperaOrarioInizio()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            return statoPartita.valueOf(rs.getString(1));
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

    public List<PartitaModel> getPartiteCreatedByUsername(String username) throws SystemException {
        String query = "SELECT nomeCampo, indirizzoCampo, data, oraInizio,  NumGiocatori, stato FROM partita WHERE creatore = ? ;";
        Connection conn = ConnectionDB.getConnection();
        List<PartitaModel> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PartitaModel partita = new PartitaModel(rs.getString(1),rs.getString(2), rs.getDate(3).toLocalDate(), username, rs.getTime(4).toLocalTime(),  rs.getInt(5), statoPartita.valueOf(rs.getString(6)));
                lista.add(partita);
            }
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        return lista;
    }

    public List<PartitaModel> getPartiteforProprietario(String username) throws SystemException {
        String query = "SELECT p.nomecampo, p.indirizzocampo, p.data, p.creatore, p.orainizio, p.numgiocatori FROM partita p JOIN campo c ON p.indirizzocampo = c.Indirizzo AND p.nomecampo = c.Nome WHERE c.Proprietario = ? AND stato = 'Pendente';";
        Connection conn = ConnectionDB.getConnection();
        List<PartitaModel> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PartitaModel partita = new PartitaModel(rs.getString(1), rs.getString(2), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getTime(5).toLocalTime(), rs.getInt(6), statoPartita.PENDENTE);
                lista.add(partita);
            }
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        return lista;
    }

    public void inviaRisposta(PartitaModel partita) throws SystemException {
        String query = "UPDATE partita SET stato = ? WHERE nomecampo = ? AND indirizzocampo = ? AND orainizio = ? AND data = ? AND creatore = ?;";
        Connection conn = ConnectionDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(partita.infoStato()));
            ps.setString(2,partita.recuperaNome());
            ps.setString(3, partita.recuperaIndirizzo());
            ps.setTime(4, Time.valueOf(partita.recuperaOrarioInizio()));
            ps.setDate(5, Date.valueOf(partita.recuperaData()));
            ps.setString(6, partita.recuperaCreatore());
            ps.executeUpdate();
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
