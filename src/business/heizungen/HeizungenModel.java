package business.heizungen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class HeizungenModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Heizungen> aussenanlagen;

    private final HeizungenDao heizungenDao;
    private static HeizungenModel aussenanlageModel;

    private HeizungenModel(HeizungenDao heizungenDao) {
        this.heizungenDao = heizungenDao;
    }

    public static HeizungenModel getInstance() throws SQLException {
        if (aussenanlageModel == null) {
            aussenanlageModel = new HeizungenModel(HeizungenDao.getInstance());
        }
        return aussenanlageModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Heizungen> aussenanlagen = heizungenDao.getAussenanlagen()
                .stream().map(Heizungen::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Heizungen> aussenanlagen) {
        List<Heizungen> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
