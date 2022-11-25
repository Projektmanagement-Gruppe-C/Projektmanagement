package business.fenster_und_Außenpposten;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fenster_AußentuerenDao {

    private static Fenster_AußentuerenDao instance;

    private final Datenbank datenbank;

    private Fenster_AußentuerenDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static Fenster_AußentuerenDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new Fenster_AußentuerenDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<Fenster_AußentuerenEntity> getAussenanlagen() {
        List<Fenster_AußentuerenEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new Fenster_AußentuerenEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aussenanlagen;
    }
}
