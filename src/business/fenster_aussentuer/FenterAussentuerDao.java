package business.fenster_aussentuer;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FenterAussentuerDao {

    private static FenterAussentuerDao instance;

    private final Datenbank datenbank;

    private FenterAussentuerDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static FenterAussentuerDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new FenterAussentuerDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<FensterAussentuerEntity> getFensterAussentuer() {
        List<FensterAussentuerEntity> fa = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Fenster_Aussentueren_Sonderwunsch");
            while (resultSet.next()) {
                fa.add(new FensterAussentuerEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fa;
    }

    public List<Integer> getFensterAussentuerListe(int kID) {
        List<Integer> fensterAussentuer_kunde_entities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT Sonderwunschid FROM Fenster_Aussentueren_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
            while (resultSet.next()) {
                fensterAussentuer_kunde_entities.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fensterAussentuer_kunde_entities;
    }

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Fenster_Aussentueren_Sonderwunsch_Kunde(sonderwunschid,Kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Fenster_Aussentueren_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
    }
}
