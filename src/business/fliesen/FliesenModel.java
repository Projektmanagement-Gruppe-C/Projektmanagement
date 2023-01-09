package business.fliesen;

import business.aussenanlage.Aussenanlage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FliesenModel {

    public static final String FLIESEN_PROPERTY = "fliesen";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Fliesen> fliesen;

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

    public List<Integer> loadFliesenListe(int kid) {
        List<Integer> fliesen_kunde = fliesenDao.getFliesenListe(kid);
        return fliesen_kunde;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
//set fliesen liste
    private void setFliesen(List<Fliesen> fliesen) {
        List<Fliesen> oldFliesen = this.fliesen;
        this.fliesen = fliesen;
        this.pcs.firePropertyChange("fliesen", oldFliesen, fliesen);
    }

    public List<Fliesen> getFliesen() {
        return fliesen;
    }

    //get alle fliesen als lsite
    public List<Fliesen> laodFliesen() {
        List<Fliesen> fliesen = fliesenDao.getFliesen()
                .stream().map(Fliesen::new).collect(Collectors.toList());
        setFliesen(fliesen);
        return fliesen;
    }
//   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        fliesenDao.speichereKundeByButton(sid,kid);
    }


    public void loescheSonderwuensche(int kid) throws SQLException {
        fliesenDao.loescheSonderwunsch(kid);
    }
}
