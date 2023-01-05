package business.grundriss;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.datenbank.Datenbank;
import business.kunde.Kunde;
import gui.grundriss.GrundrissView;

import javax.swing.*;
import java.io.*;

public class GrundrissModel {
    private Grundriss grundriss;
    private static GrundrissModel instance;
    private final GrundrissDao grundrissDao;
    private final List<Grundriss> grundrisse = new ArrayList<>();
    private final List<Grundriss> grundrisseForKunde = new ArrayList<>();

    private Datenbank db;

    private Kunde kunde;

    private GrundrissModel() throws SQLException, ClassNotFoundException {
        grundrissDao = GrundrissDao.getInstance();
        grundrisse.addAll(grundrissDao.getGrundrisse());
        db = Datenbank.getInstance();
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
        String query = "";
        query = "INSERT INTO kunde_sw_gr(kundennummer, sonderwunschid, hausnummer) ";
        query += "VALUES(" + this.kunde.getKundennummer() + ", ";
        query += sonderwunschId + ", ";
        query += this.kunde.getHausnummer() + ");";

        try {
            db.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sonderwuensche konnten nicht in die DB geschrieben werden", "Fehler", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static GrundrissModel getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new GrundrissModel();
        }
        return instance;
    }

}
