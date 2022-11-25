package business.sanitaer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SanitaerModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Sanitaer> aussenanlagen;

    private final SanitaerDao sanitaerDao;
    private static SanitaerModel sanitaerModel;

    private SanitaerModel(SanitaerDao sanitaerDao) {
        this.sanitaerDao = sanitaerDao;
    }

    public static SanitaerModel getInstance() throws SQLException {
        if (sanitaerModel == null) {
            sanitaerModel = new SanitaerModel(SanitaerDao.getInstance());
        }
        return sanitaerModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadAussenanlagen() {
        List<Sanitaer> aussenanlagen = sanitaerDao.getAussenanlagen()
                .stream().map(Sanitaer::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
    }

    private void setAussenanlagen(List<Sanitaer> aussenanlagen) {
        List<Sanitaer> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }
}
