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

    public List<ParkettEntity> getAussenanlagen() {
        List<ParkettEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new ParkettEntity(
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
