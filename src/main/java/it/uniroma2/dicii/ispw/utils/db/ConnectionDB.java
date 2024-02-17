package it.uniroma2.dicii.ispw.utils.db;



import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static Connection connection;

    private ConnectionDB() {}


    public static Connection getConnection() throws SystemException {
        if(connection ==null){
            try (InputStream input = new FileInputStream("src/main/java/it/uniroma2/dicii/ispw/utils/db/config.properties")) {
                Properties properties = new Properties();
                properties.load(input);

                String connectionUrl = properties.getProperty("DB_URL");
                String user = properties.getProperty("USER");
                String pass = properties.getProperty("PASS");

                connection = DriverManager.getConnection(connectionUrl, user, pass);
            } catch (IOException | SQLException e) {
                SystemException exception = new SystemException();
                exception.initCause(e);
                throw exception;
            }
        }
        return connection;
    }

    public void closeConnection() throws SQLException { connection.close(); }
}
