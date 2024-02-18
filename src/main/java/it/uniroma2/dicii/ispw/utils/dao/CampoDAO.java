package it.uniroma2.dicii.ispw.utils.dao;

import it.uniroma2.dicii.ispw.model.CampoModel;
import it.uniroma2.dicii.ispw.model.ProprietarioModel;
import it.uniroma2.dicii.ispw.utils.bean.ConverterBean;
import it.uniroma2.dicii.ispw.utils.db.ConnectionDB;
import it.uniroma2.dicii.ispw.utils.engineering.ConverterToFileEngineering;
import it.uniroma2.dicii.ispw.utils.exceptions.CampoEsistenteException;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampoDAO {
    private static final String PATHCAMPIIMG = "campi_img/";


    public List<CampoModel> getRichiesteCampo() throws SystemException {
        String query="SELECT * FROM richiestacampo;";
        Connection conn= ConnectionDB.getConnection();
        List<CampoModel> lista=new ArrayList<>();
        CampoModel campo=null;
        try(PreparedStatement ps= conn.prepareStatement(query)){

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InputStream inputStream = (rs.getBinaryStream(6));
                String filePath = PATHCAMPIIMG + rs.getString(1) + "pic" + ".png";
                File file = new File(filePath);
                ConverterToFileEngineering converterToFile = new ConverterToFileEngineering();
                converterToFile.fromInputStreamToFile(new ConverterBean(inputStream, file));
                campo = new CampoModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getTime(4),rs.getTime(5),rs.getString(8),file);
                campo.impostaNumeroCampo(rs.getInt(9));
                lista.add(campo);
            }
            return lista;

        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }



    }

    public void tryInsertRichiestaCampo(CampoModel campo, ProprietarioModel proprietario) throws SystemException, CampoEsistenteException {

        String query1 = "SELECT max(numero) FROM campo WHERE indirizzo = ?;";
        int maxCampi = 0;
        Connection conn = ConnectionDB.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(query1)) {

            ps.setString(1, campo.recuperaIndirizzo());

            ResultSet rs = ps.executeQuery();
            rs.next();
            maxCampi = rs.getInt(1);

            if(maxCampi!=0){
                int maxRichieste = this.getMaxNumero(campo);
                throw new CampoEsistenteException(Integer.toString(Math.max(maxCampi, maxRichieste)));
            }



        } catch (SystemException exc) {                 //Vuol dire che non c'è una richiesta con quel campo
            throw new CampoEsistenteException(Integer.toString(maxCampi));

        } catch (SQLException e) {
            throw new SystemException();
        }



            String query = "INSERT INTO richiestacampo VALUES(?,?,?,?,?,?,?,?,?);";

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, campo.nomeAttuale());
                ps.setString(2, campo.recuperaIndirizzo());
                ps.setInt(3, campo.costoOrario());
                ps.setTime(4, campo.inizioAttivita());
                ps.setTime(5, campo.fineAttivita());
                ps.setBlob(6, new FileInputStream(campo.recuperaImmagine()));
                ps.setString(7, proprietario.getUsername());
                ps.setString(8, campo.credPagamento());
                ps.setString(8, campo.credPagamento());
                ps.setInt(9, campo.numeroCampo());
                ps.executeUpdate();


            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    throw new CampoEsistenteException();
                } else {
                    SystemException exception = new SystemException();
                    exception.initCause(e);
                    throw exception;
                }
            } catch (FileNotFoundException exc) {
                SystemException exception = new SystemException();
                exception.initCause(exc);
                throw exception;
            }
        }
    public void insertRichiestaCampo(CampoModel campo, ProprietarioModel proprietario) throws SystemException {

        String insert = "INSERT INTO richiestacampo VALUES(?,?,?,?,?,?,?,?,?);";
        Connection conn = ConnectionDB.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(insert)) {
            preparedStatement.setString(1, campo.nomeAttuale());
            preparedStatement.setString(2, campo.recuperaIndirizzo());
            preparedStatement.setInt(3, campo.costoOrario());
            preparedStatement.setTime(4, campo.inizioAttivita());
            preparedStatement.setTime(5, campo.fineAttivita());
            preparedStatement.setBlob(6, new FileInputStream(campo.recuperaImmagine()));
            preparedStatement.setString(7, proprietario.getUsername());
            preparedStatement.setString(8, campo.credPagamento());
            preparedStatement.setString(8, campo.credPagamento());
            preparedStatement.setInt(9, campo.numeroCampo());

            preparedStatement.executeUpdate();


        } catch (SQLException | FileNotFoundException e) {

                SystemException exception = new SystemException();
                exception.initCause(e);
                throw exception;
            }

    }


    public void eliminaRichiesta(CampoModel campo) throws SystemException {
        String query="DELETE FROM richiestacampo WHERE nome= ? and indirizzo = ?;";
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());
            ps.executeUpdate();


        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }

    public ProprietarioModel getProprietarioFromRichiestaCampo(CampoModel campo) throws SystemException {
        String query="SELECT proprietario, iban FROM richiestacampo WHERE nome= ? and indirizzo =?;";
        Connection conn= ConnectionDB.getConnection();
        ProprietarioModel proprietario=null;
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());

            ResultSet rs = ps.executeQuery();
            rs.next();
            proprietario=new ProprietarioModel(rs.getString(1),rs.getString(2));

        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        return proprietario;
    }

    public void aggiungiCampo(CampoModel campo, ProprietarioModel proprietario) throws SystemException {
        String query="INSERT INTO campo VALUES(?,?,?,?,?,?,?,?,?);";
        Connection conn= ConnectionDB.getConnection();
        try(PreparedStatement ps= conn.prepareStatement(query)){
            ps.setString(1,campo.nomeAttuale());
            ps.setString(2,campo.recuperaIndirizzo());
            ps.setInt(3,campo.costoOrario());
            ps.setTime(4,campo.inizioAttivita());
            ps.setTime(5,campo.fineAttivita());
            ps.setTime(4,campo.inizioAttivita());
            ps.setBlob(6, new FileInputStream(campo.recuperaImmagine()));
            ps.setString(7,proprietario.getUsername());
            ps.setString(8,proprietario.credBancarie());
            ps.setInt(9,campo.numeroCampo());

            ps.executeUpdate();


        }catch(SQLException | FileNotFoundException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }

    }

    public int getMaxNumero(CampoModel campo) throws SystemException{
        String query= "SELECT max(numero) FROM richiestacampo WHERE indirizzo = ?;";
        int max;
        Connection conn= ConnectionDB.getConnection();

        try(PreparedStatement ps= conn.prepareStatement(query)){

            ps.setString(1,campo.recuperaIndirizzo());

            ResultSet rs = ps.executeQuery();
            rs.next();
            max=rs.getInt(1);

        }catch(SQLException e){
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        return max;
    }

    }







