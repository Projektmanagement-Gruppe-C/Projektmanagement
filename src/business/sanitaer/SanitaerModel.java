package business.sanitaer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SanitaerModel {

    public static final String SANITAER_PROPERTY = "sanitaer";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Sanitaer> sanitaer;

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

    public List<Integer> loadSanitaerListe(int kid) {
        List<Integer> sanitaer_kunde = sanitaerDao.getSanitaerListe(kid);
        return sanitaer_kunde;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
//set fliesen liste
    private void setSanitaer(List<Sanitaer> sanitaer) {
        List<Sanitaer> oldSanitaer = this.sanitaer;
        this.sanitaer = sanitaer;
        this.pcs.firePropertyChange("sanitaer", oldSanitaer, sanitaer);
    }

    public List<Sanitaer> getSanitaer() {
        return sanitaer;
    }

    //get alle fliesen als lsite
    public List<Sanitaer> laodSanitaer() {
        List<Sanitaer> sanitaer = sanitaerDao.getSanitaer()
                .stream().map(Sanitaer::new).collect(Collectors.toList());
        setSanitaer(sanitaer);
        return sanitaer;
    }
//   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        sanitaerDao.speichereKundeByButton(sid,kid);
    }

    public void loescheSonderwuensche(int kid) throws SQLException {
        sanitaerDao.loescheSonderwunsch(kid);
    }
}
