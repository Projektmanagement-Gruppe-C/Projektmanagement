package business.heizungen;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeizungenDao {

    private static HeizungenDao instance;

    private final Datenbank datenbank;

    private HeizungenDao(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public static HeizungenDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new HeizungenDao(Datenbank.getInstance());
        }
        return instance;
    }

    public List<HeizungenEntity> getAussenanlagen() {
        List<HeizungenEntity> aussenanlagen = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Außenanlage_Sonderwunsch");
            while (resultSet.next()) {
                aussenanlagen.add(new HeizungenEntity(
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
