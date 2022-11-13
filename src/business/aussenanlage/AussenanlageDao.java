package business.aussenanlage;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AussenanlageDao {

    private static AussenanlageDao instance;

    private final Datenbank datenbank;

    private AussenanlageDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static AussenanlageDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new AussenanlageDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<AussenanlageEntity> getAussenanlagen() {
        List<AussenanlageEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new AussenanlageEntity(
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
