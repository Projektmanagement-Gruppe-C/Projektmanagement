package business.grundriss;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import business.kunde.Kunde;
import gui.grundriss.GrundrissView;
import java.io.*;

public class GrundrissModel {
    private Grundriss grundriss;
    private static GrundrissModel instance;
    private final GrundrissDao grundrissDao;
    private final List<Grundriss> grundrisse = new ArrayList<>();
    private final List<Grundriss> grundrisseForKunde = new ArrayList<>();
    private Kunde kunde;

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
    public void setKundeSonderwunsch(int sonderwunschId) {
        String query =
                "INSERT INTO kunde_sw_gr(kundennummer, sonderwunschid, hausnummer) "
                        + "VALUES('" + this.kunde.getKundennummer() + "', "
                        + "'" + sonderwunschId + "', "
                        + "'" + this.kunde.getHausnummer() +"');";
    }
    public static GrundrissModel getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new GrundrissModel();
        }
        return instance;
    }

}
