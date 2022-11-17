package business.grundriss;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrundrissModel {

    private static GrundrissModel instance;
    private final GrundrissDao grundrissDao;
    private final List<Grundriss> grundrisse = new ArrayList<>();
    private final List<Grundriss> grundrisseForKunde = new ArrayList<>();

    private GrundrissModel() throws SQLException, ClassNotFoundException {
        grundrissDao = GrundrissDao.getInstance();
        grundrisse.addAll(grundrissDao.getGrundrisse());
    }

    public List<Grundriss> getGrundrisse() {
        return grundrisse;
    }

    public List<Grundriss> getGrundrisseForKunde(int planNr) {
        return grundrisseForKunde;
    }

    public void loadKundenData(int kundenNr) {
        grundrisseForKunde.clear();
        grundrisseForKunde.addAll(grundrissDao.getGrundrisseForKunde(kundenNr));
    }

    public static GrundrissModel getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new GrundrissModel();
        }
        return instance;
    }
}
