package business.fliesen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FliesenModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Fliesen> aussenanlagen;

    private final FliesenDao fliesenDao;
    private static FliesenModel fliesenModel;

    private FliesenModel(FliesenDao fliesenDao) {
        this.fliesenDao = fliesenDao;
    }

    public static FliesenModel getInstance() throws SQLException {
        if (fliesenModel == null) {
            fliesenModel = new FliesenModel(FliesenDao.getInstance());
        }
        return fliesenModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Fliesen> aussenanlagen = fliesenDao.getAussenanlagen()
                .stream().map(Fliesen::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Fliesen> aussenanlagen) {
        List<Fliesen> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
