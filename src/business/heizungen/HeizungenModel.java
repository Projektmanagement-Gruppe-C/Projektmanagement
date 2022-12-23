package business.heizungen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class HeizungenModel {

    public static final String HEIZUNGEN_PROPERTY = "heizungen";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Heizungen> heizungen;

    private final HeizungenDao heizungenDao;
    private static HeizungenModel heizungenModel;

    private HeizungenModel(HeizungenDao heizungenDao) {
        this.heizungenDao = heizungenDao;
    }

    public static HeizungenModel getInstance() throws SQLException {
        if (heizungenModel == null) {
            heizungenModel = new HeizungenModel(HeizungenDao.getInstance());
        }
        return heizungenModel;
    }

    public List<Integer> loadHeizungenListe(int kid) {
        List<Integer> heizungen_kunde = heizungenDao.getHeizungenListe(kid);
        return heizungen_kunde;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    private void setHeizungen(List<Heizungen> heizungen) {
        List<Heizungen> oldHeizungen = this.heizungen;
        this.heizungen = heizungen;
        this.pcs.firePropertyChange("heizungen", oldHeizungen, heizungen);
    }

    public List<Heizungen> getHeizungen() {
        return heizungen;
    }

    //get alle fliesen als lsite
    public List<Heizungen> laodHeizungen() {
        List<Heizungen> heizungen = heizungenDao.getHeizungen()
                .stream().map(Heizungen::new).collect(Collectors.toList());
        setHeizungen(heizungen);
        return heizungen;
    }
//   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        heizungenDao.speichereKundeByButton(sid,kid);
    }


    public void loescheSonderwuensche(int kid) throws SQLException {
        heizungenDao.loescheSonderwunsch(kid);
    }
}
