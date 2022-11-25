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

    public List<SanitaerEntity> getAussenanlagen() {
        List<SanitaerEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new SanitaerEntity(
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
