package business.innentureen;

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
        List<InnentuerenEntity> innentuerenEntities = new ArrayList<>();
        try {
            ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Innentüren_Sonderwunsch");
            while (resultSet.next()) {
                innentuerenEntities.add(new InnentuerenEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return innentuerenEntities;
    }
}
