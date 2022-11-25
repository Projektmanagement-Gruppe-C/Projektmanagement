package business.grundriss;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GrundrissModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Grundriss> aussenanlagen;

    private final GrundrissDao grundrissDao;
    private static GrundrissModel grundrissModel;

    private GrundrissModel(GrundrissDao grundrissDao) {
        this.grundrissDao = grundrissDao;
    }

    public static GrundrissModel getInstance() throws SQLException {
        if (grundrissModel == null) {
            grundrissModel = new GrundrissModel(GrundrissDao.getInstance());
        }
        return grundrissModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Grundriss> aussenanlagen = grundrissDao.getAussenanlagen()
                .stream().map(Grundriss::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Grundriss> aussenanlagen) {
        List<Grundriss> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
