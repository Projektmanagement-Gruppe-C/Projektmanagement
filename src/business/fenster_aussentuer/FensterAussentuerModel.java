package business.fenster_aussentuer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FensterAussentuerModel {

    public static final String FENSTER_AUSSENTUER_PROPERTY = "fenster_aussentuer";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<FenterAussentuer> fenterAussentuer;

    private final FenterAussentuerDao fenterAussentuerDao;
    private static FensterAussentuerModel fensterAussentuerModel;

    private FensterAussentuerModel(FenterAussentuerDao fenterAussentuerDao) {
        this.fenterAussentuerDao = fenterAussentuerDao;
    }

    public static FensterAussentuerModel getInstance() throws SQLException {
        if (fensterAussentuerModel == null) {
            fensterAussentuerModel = new FensterAussentuerModel(FenterAussentuerDao.getInstance());
        }
        return fensterAussentuerModel;
    }

    public List<Integer> loadFensterAussentuerListe(int kid) {
        List<Integer> fenster_aussentuer_kunde = fenterAussentuerDao.getFensterAussentuerListe(kid);
        return fenster_aussentuer_kunde;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
//set fliesen liste
    private void setFenterAussentuer(List<FenterAussentuer> fenterAussentuer) {
        List<FenterAussentuer> oldFenterAussentuer = this.fenterAussentuer;
        this.fenterAussentuer = fenterAussentuer;
        this.pcs.firePropertyChange("fenster_aussentuer", oldFenterAussentuer, fenterAussentuer);
    }

    public List<FenterAussentuer> getFenterAussentuer() {
        return fenterAussentuer;
    }

    //get alle fliesen als lsite
    public List<FenterAussentuer> laodFensterAussentuer() {
        List<FenterAussentuer> fenterAussentuer = fenterAussentuerDao.getFensterAussentuer()
                .stream().map(FenterAussentuer::new).collect(Collectors.toList());
        setFenterAussentuer(fenterAussentuer);
        return fenterAussentuer;
    }
//   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        fenterAussentuerDao.speichereKundeByButton(sid,kid);
    }


    public void loescheSonderwuensche(int kid) throws SQLException {
        fenterAussentuerDao.loescheSonderwunsch(kid);
    }
}
