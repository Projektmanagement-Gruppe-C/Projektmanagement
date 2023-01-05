package business.grundriss;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrundrissDao {
    private static GrundrissDao instance;
    private final Datenbank datenbank;

    private GrundrissDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static GrundrissDao getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new GrundrissDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<Grundriss> getGrundrisse() {
        String query = "SELECT * FROM Grundriss_Sonderwunsch";
        return getGrundrisses(query);
    }

    public List<Grundriss> getGrundrisseForKunde(int kundeID) {
        String query = "SELECT GS.* FROM " +
                "Grundriss_Sonderwunsch_Kunde GSK " +
                "INNER JOIN Grundriss_Sonderwunsch GS on GSK.Sonderwunschid = GS.Id " +
                "WHERE GSK.Kundeid = " + kundeID;
        return getGrundrisses(query);
    }

    private List<Grundriss> getGrundrisses(String query) {
        List<Grundriss> grundrisse = new ArrayList<>();
        try (ResultSet resultSet = datenbank.executeQuery(query)) {
            while (resultSet.next()) {
                GrundrissEntity grundrissEntity = new GrundrissEntity(
                        resultSet.getInt("Id"),
                        resultSet.getString("Beschreibung"),
                        resultSet.getInt("Preis")
                );
                Grundriss grundriss = new Grundriss(grundrissEntity);
                grundrisse.add(grundriss);
            }
            return grundrisse;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
