package business.heizungen;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeizungenDao {

    private static HeizungenDao instance;

    private final Datenbank datenbank;

    private HeizungenDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static HeizungenDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new HeizungenDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<HeizungenEntity> getHeizungen() {
        List<HeizungenEntity> heizungen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Heizungen_Sonderwunsch");
            while (resultSet.next()) {
                heizungen.add(new HeizungenEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heizungen;
    }

    public List<Integer> getHeizungenListe(int kID) {
        List<Integer> heizungen_kunde_entities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT Sonderwunschid FROM Heizungen_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
            while (resultSet.next()) {
                heizungen_kunde_entities.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heizungen_kunde_entities;
    }

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Heizungen_Sonderwunsch_Kunde(sonderwunschid,Kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Heizungen_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
        System.out.println(resultSet);

    }
}
