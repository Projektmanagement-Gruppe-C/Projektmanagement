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

    public static GrundrissDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new GrundrissDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<GrundrissEntity> getAussenanlagen() {
        List<GrundrissEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new GrundrissEntity(
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
