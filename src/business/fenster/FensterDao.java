package business.fenster;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FensterDao {

    private static FensterDao instance;

    private final Datenbank datenbank;

    private FensterDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static FensterDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new FensterDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<FensterEntity> getAussenanlagen() {
        List<FensterEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new FensterEntity(
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
