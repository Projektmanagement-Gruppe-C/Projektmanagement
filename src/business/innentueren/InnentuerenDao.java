package business.innentueren;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InnentuerenDao {

    private static InnentuerenDao instance;

    private final Datenbank datenbank;

    private InnentuerenDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static InnentuerenDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new InnentuerenDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<InnentuerenEntity> getInnentueren() {
        List<InnentuerenEntity>    innentueren = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Innentuer_Sonderwunsch");
            while (resultSet.next()) {
                innentueren.add(new InnentuerenEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return innentueren;
    }

    public List<Integer> getInnentuerenListe(int kID) {
        List<Integer> innentueren_kunde_entities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT Sonderwunschid FROM Innentuer_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
            while (resultSet.next()) {
                innentueren_kunde_entities.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return innentueren_kunde_entities;
    }

    public void loescheSonderwunsch(int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Innentuer_Sonderwunsch_Kunde WHERE Kundeid="+kID+"\n");
    }

    public void speichereKundeByButton(int sID,int kID) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Innentuer_Sonderwunsch_Kunde(Sonderwunschid,Kundeid) VALUES ("+sID+", "+kID+");");
        System.out.println(resultSet);
    }


}
