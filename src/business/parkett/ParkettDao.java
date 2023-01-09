package business.parkett;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkettDao {

    private static ParkettDao instance;

    private final Datenbank datenbank;

    private ParkettDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static ParkettDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new ParkettDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<ParkettEntity> getParkett() {
        List<ParkettEntity> parkett = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Parkett_Sonderwunsch");
            while (resultSet.next()) {
                parkett.add(new ParkettEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkett;
    }

    public List<Integer> getParkettListe(int kID) {
        List<Integer> parkett_kunde_entities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT Sonderwunschid FROM Parkett_Sonderwunsch_Kunde WHERE kundeid="+kID+"\n");
            while (resultSet.next()) {
                parkett_kunde_entities.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkett_kunde_entities;
    }

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Parkett_Sonderwunsch_Kunde(sonderwunschid,kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Parkett_Sonderwunsch_Kunde WHERE kundeid="+kID+"\n");
    }
}
