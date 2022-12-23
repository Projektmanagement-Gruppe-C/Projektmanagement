package business.parkett;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ParkettModel {

    public static final String PARKETT_PROPERTY = "parkett";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<Parkett> parkett;

    private final ParkettDao parkettDao;
    private static ParkettModel parkettModel;

    private ParkettModel(ParkettDao parkettDao) {
        this.parkettDao = parkettDao;
    }

    public static ParkettModel getInstance() throws SQLException {
        if (parkettModel == null) {
            parkettModel = new ParkettModel(ParkettDao.getInstance());
        }
        return parkettModel;
    }

    public List<Integer> loadParkettListe(int kid) {
        List<Integer> parkett_kunde = parkettDao.getParkettListe(kid);
        return parkett_kunde;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
//set fliesen liste
    private void setParkett(List<Parkett> parkett) {
        List<Parkett> oldParkett = this.parkett;
        this.parkett = parkett;
        this.pcs.firePropertyChange("parkett", oldParkett, parkett);
    }

    public List<Parkett> getParkett() {
        return parkett;
    }

    //get alle Parkett als lsite
    public List<Parkett> laodParkett() {
        List<Parkett> parkett = parkettDao.getParkett()
                .stream().map(Parkett::new).collect(Collectors.toList());
        setParkett(parkett);
        return parkett;
    }
//   gibt die aufgabe an die dao weiter
    public void speichereSonderwuensche(int sid,int kid) throws SQLException{
        parkettDao.speichereKundeByButton(sid,kid);
    }


    public void loescheSonderwuensche(int kid) throws SQLException {
        parkettDao.loescheSonderwunsch(kid);
    }
}
