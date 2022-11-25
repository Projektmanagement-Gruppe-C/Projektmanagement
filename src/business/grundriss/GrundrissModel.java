package business.grundriss;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GrundrissModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Grundriss> grundriss;

    private final GrundrissDao grundrissDao;
    private static GrundrissModel grundrissModel;

    private GrundrissModel(GrundrissDao aussenanlageDao) {
        this.grundrissDao = aussenanlageDao;
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
        List<Grundriss> grundriss = grundrissDao.getAussenanlagen()
                .stream().map(Grundriss::new).collect(Collectors.toList());
        setAussenanlagen(grundriss);
    }

    private void setAussenanlagen(List<Grundriss> aussenanlagen) {
        List<Grundriss> oldgrundriss = this.grundriss;
        this.grundriss = grundriss;
        this.pcs.firePropertyChange("aussenanlage", oldgrundriss, aussenanlagen);
    }
}
