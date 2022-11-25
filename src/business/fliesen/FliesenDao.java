package business.fliesen;

import business.datenbank.Datenbank;

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

    public List<FliesenEntity> getAussenanlagen() {
        List<FliesenEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new FliesenEntity(
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
