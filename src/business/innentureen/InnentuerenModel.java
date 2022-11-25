package business.innentureen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class InnentuerenModel {

    public static final String AUSSENANLAGE_PROPERTY = "aussenanlage";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Innentueren> innentueren;

    private final InnentuerenDao innentuerenDao;
    private static InnentuerenModel innentuerenModel;

    private InnentuerenModel(InnentuerenDao innentuerenDao) {
        this.innentuerenDao = innentuerenDao;
    }

    public static InnentuerenModel getInstance() throws SQLException {
        if (innentuerenModel == null) {
            innentuerenModel = new InnentuerenModel(InnentuerenDao.getInstance());
        }
        return innentuerenModel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void loadInnentueren() {
        List<Innentueren> innentueren = innentuerenDao.getInnentueren()
                .stream().map(Innentueren::new).collect(Collectors.toList());
        setInnentueren(innentueren);
    }

    private void setInnentueren(List<Innentueren> aussenanlagen) {
        List<Innentueren> oldInnenT = this.innentueren;
        this.innentueren = aussenanlagen;
        this.pcs.firePropertyChange("aussenanlage", oldInnenT, aussenanlagen);
    }
}
