package business.fenster;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FensterModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Fenster> aussenanlagen;

    private final FensterDao fensterDao;
    private static FensterModel fensterModel;

    private FensterModel(FensterDao fensterDao) {
        this.fensterDao = fensterDao;
    }

    public static FensterModel getInstance() throws SQLException {
        if (fensterModel == null) {
            fensterModel = new FensterModel(FensterDao.getInstance());
        }
        return fensterModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Fenster> aussenanlagen = fensterDao.getAussenanlagen()
                .stream().map(Fenster::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Fenster> aussenanlagen) {
        List<Fenster> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
