package business.kunde;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KundeDao {
    private static KundeDao instance;
    private final Datenbank datenbank;

    public KundeEntity getKundeByPlanNr(int planNr) {
        try (ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Kunde WHERE planNr = " + planNr)) {
            if (resultSet.next()) {
                return new KundeEntity(
                        resultSet.getInt("KundeID"),
                        resultSet.getString("Vorname"),
                        resultSet.getString("Nachname"),
                        resultSet.getString("Email"),
                        resultSet.getString("TelefonNr"),
                        resultSet.getInt("PlanNr")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private KundeDao(
            Datenbank datenbank
    ) {
        this.datenbank = datenbank;
    }

    public static KundeDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new KundeDao(Datenbank.getInstance());
        }
        return instance;
    }
}
