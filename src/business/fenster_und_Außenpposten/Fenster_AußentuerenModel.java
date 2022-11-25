package business.fenster_und_Außenpposten;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Fenster_AußentuerenModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Fenster_Außentueren> aussenanlagen;

    private final Fenster_AußentuerenDao fensterAußentuerenDao;
    private static Fenster_AußentuerenModel fensterAußentuerenModel;

    private Fenster_AußentuerenModel(Fenster_AußentuerenDao fensterAußentuerenDao) {
        this.fensterAußentuerenDao = fensterAußentuerenDao;
    }

    public static Fenster_AußentuerenModel getInstance() throws SQLException {
        if (fensterAußentuerenModel == null) {
            fensterAußentuerenModel = new Fenster_AußentuerenModel(Fenster_AußentuerenDao.getInstance());
        }
        return fensterAußentuerenModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Fenster_Außentueren> aussenanlagen = fensterAußentuerenDao.getAussenanlagen()
                .stream().map(Fenster_Außentueren::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Fenster_Außentueren> aussenanlagen) {
        List<Fenster_Außentueren> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
