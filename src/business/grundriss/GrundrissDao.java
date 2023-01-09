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

    public List<GrundrissEntity> getGrundriss() {
        List<GrundrissEntity> grundriss = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Grundriss_Sonderwunsch");
            while (resultSet.next()) {
                grundriss.add(new GrundrissEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grundriss;
    }

    public List<Integer> getGrundrissListe(int kID) {
        List<Integer> grundriss_kunde_entities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT Sonderwunschid FROM Grundriss_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
            while (resultSet.next()) {
                grundriss_kunde_entities.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grundriss_kunde_entities;
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

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Grundriss_Sonderwunsch_Kunde(sonderwunschid,Kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Grundriss_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
    }
}
