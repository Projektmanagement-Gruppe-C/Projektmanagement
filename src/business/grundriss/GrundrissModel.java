package business.grundriss;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GrundrissModel {

    public static final String GRUNDRISS_PROPERTY = "grundriss";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Grundriss> grundriss;

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

    public List<Integer> loadGrundrissnListe(int kid) {
        List<Integer> grundriss_kunde = grundrissDao.getGrundrissListe(kid);
        return grundriss_kunde;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    private void setGrundriss(List<Grundriss> grundriss) {
        List<Grundriss> oldGrundriss = this.grundriss;
        this.grundriss = grundriss;
        this.pcs.firePropertyChange("grundriss", oldGrundriss, grundriss);
    }

    public List<Grundriss> getGrundriss() {
        return grundriss;
    }

    //get alle fliesen als lsite
    public List<Grundriss> loadGrundriss() {
        List<Grundriss> grundriss = grundrissDao.getGrundriss()
                .stream().map(Grundriss::new).collect(Collectors.toList());
        setGrundriss(grundriss);
        return grundriss;
    }
//   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        grundrissDao.speichereKundeByButton(sid,kid);
    }


    public void loescheSonderwuensche(int kid) throws SQLException {
        grundrissDao.loescheSonderwunsch(kid);
    }
}
