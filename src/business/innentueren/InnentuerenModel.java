package business.innentueren;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class InnentuerenModel {

    public static final String INNENTUER_PROPERTY = "innentuer";

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

    private void setInnentueren(List<Innentueren> innentueren) {
        List<Innentueren> oldInnentueren = this.innentueren;
        this.innentueren = innentueren;
        this.pcs.firePropertyChange("innentuer", oldInnentueren, innentueren);
    }

    public List<Innentueren> loadInnentueren() {
        List<Innentueren> innentuerens = innentuerenDao.getInnentueren()
                .stream().map(Innentueren::new).collect(Collectors.toList());
        setInnentueren(innentuerens);
        return innentuerens;
    }

    public List<Integer> loadInnentuerenListe(int kID) {
        List<Integer> innentuer_kunde = innentuerenDao.getInnentuerenListe(kID);
        return innentuer_kunde;
    }

    public List<Innentueren> getInnentueren() {
        return innentueren;
    }

    //   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        innentuerenDao.speichereKundeByButton(sid,kid);
    }

//löscht alle sonderwunsche für außenanlage von kunde kid
    public void loescheSonderwuensche(int kid) throws SQLException {
        innentuerenDao.loescheSonderwunsch(kid);
    }

}
