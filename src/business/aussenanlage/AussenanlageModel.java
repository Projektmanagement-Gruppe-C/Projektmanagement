package business.aussenanlage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AussenanlageModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Aussenanlage> aussenanlagen;

    private final AussenanlageDao aussenanlageDao;
    private static AussenanlageModel aussenanlageModel;

    private AussenanlageModel(AussenanlageDao aussenanlageDao) {
        this.aussenanlageDao = aussenanlageDao;
    }

    public static AussenanlageModel getInstance() throws SQLException {
        if (aussenanlageModel == null) {
            aussenanlageModel = new AussenanlageModel(AussenanlageDao.getInstance());
        }
        return aussenanlageModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    private void setAussenanlagen(List<Aussenanlage> aussenanlagen) {
        List<Aussenanlage> oldAnlagen = this.aussenanlagen;
        this.aussenanlagen = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldAnlagen, aussenanlagen);
    }

    public List<Aussenanlage> loadAussenanlagen() {
        List<Aussenanlage> aussenanlagen = aussenanlageDao.getAussenanlagen()
                .stream().map(Aussenanlage::new).collect(Collectors.toList());
        setAussenanlagen(aussenanlagen);
        return aussenanlagen;
    }

    public List<Integer> loadAussenanlagenListe(int kID) {
        List<Integer> aussenanlagen_kunde = aussenanlageDao.getAussenanlagenListe(kID);
        return aussenanlagen_kunde;
    }
}
