package business.fliesen;

import business.datenbank.Datenbank;
import business.kunde.Kunde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FliesenDao {

    private static FliesenDao instance;

    private final Datenbank datenbank;

    private FliesenDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static FliesenDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new FliesenDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<FliesenEntity> getFliesen() {
        List<FliesenEntity> fliesen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Fliesen_Sonderwunsch");
            while (resultSet.next()) {
                fliesen.add(new FliesenEntity(
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

    public List<Integer> getFliesenListe(int kID) {
        List<Integer> fliesen_kunde_entities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT Sonderwunschid FROM Fliesen_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
            while (resultSet.next()) {
                fliesen_kunde_entities.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fliesen_kunde_entities;
    }

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Fliesen_Sonderwunsch_Kunde(sonderwunschid,Kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Fliesen_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
    }
}
