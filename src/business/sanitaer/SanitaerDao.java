package business.sanitaer;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanitaerDao {

    private static SanitaerDao instance;

    private final Datenbank datenbank;

    private SanitaerDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static SanitaerDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new SanitaerDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<SanitaerEntity> getSanitaer() {
        List<SanitaerEntity> fliesen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Sanitaer_Sonderwunsch");
            while (resultSet.next()) {
                fliesen.add(new SanitaerEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fliesen;
    }

    public List<Integer> getSanitaerListe(int kID) {
        List<Integer> sanitaer_kunde = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT sonderwunschid FROM Sanitaer_Sonderwunsch_Kunde WHERE kundeid="+kID+"\n");
            while (resultSet.next()) {
                sanitaer_kunde.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanitaer_kunde;
    }

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Sanitaer_Sonderwunsch_Kunde(sonderwunschid,kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Sanitaer_Sonderwunsch_Kunde WHERE kundeid="+kID+"\n");
    }
}
