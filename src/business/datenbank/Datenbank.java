package business.datenbank;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datenbank {
    private Connection connection;

    public void getDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        connection = DriverManager.getConnection(url, dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
    }

    public int executeQuery(String query) throws SQLException {
        return connection.createStatement().executeUpdate(query);
    }
}
