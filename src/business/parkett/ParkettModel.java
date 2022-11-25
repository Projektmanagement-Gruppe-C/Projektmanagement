package business.parkett;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ParkettModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Parkett> aussenanlagen;

    private final ParkettDao parkettDao;
    private static ParkettModel parkettModel;

    private ParkettModel(ParkettDao parkettDao) {
        this.parkettDao = parkettDao;
    }

    public static ParkettModel getInstance() throws SQLException {
        if (parkettModel == null) {
            parkettModel = new ParkettModel(ParkettDao.getInstance());
        }
        return parkettModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Parkett> aussenanlagen = parkettDao.getAussenanlagen()
                .stream().map(Parkett::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Parkett> aussenanlagen) {
        List<Parkett> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
