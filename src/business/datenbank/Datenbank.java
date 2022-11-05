package business.datenbank;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datenbank {
    private static Datenbank instance;

    private Datenbank() throws SQLException {
        getDBConnection();
    }
    private Connection connection;

    private void getDBConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        connection = DriverManager.getConnection(url, dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        return connection.createStatement().executeUpdate(query);
    }

    public static Datenbank getInstance() throws SQLException {
        if (instance == null) {
            instance = new Datenbank();
        }
        return instance;
    }
}
