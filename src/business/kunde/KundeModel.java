package business.kunde;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import javafx.collections.*;
  
/** 
 * Klasse, welche das Model des Grundfensters mit den Kundendaten enthaelt.
 */
public class KundeModel {

	public static final String KUNDE_PROPERTY = "kunde";
	
	// enthaelt den aktuellen Kunden
	private Kunde kunde;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	private void setKunde(Kunde kunde) {

		Kunde oldKunde = this.kunde;
		this.kunde = kunde;
		this.pcs.firePropertyChange("kunde", oldKunde, kunde);

	}

	public KundeDao kundeDao;
	
	/* enthaelt die Plannummern der Haeuser, diese muessen vielleicht noch
	   in eine andere Klasse verschoben werden */
	ObservableList<Integer> plannummern =
	  FXCollections.observableArrayList(
		0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
	

	// enthaelt das einzige KundeModel-Objekt
	private static KundeModel kundeModel;
	
	// privater Konstruktor zur Realisierung des Singleton-Pattern
	private KundeModel(KundeDao kundeDao) {
		super();
		this.kundeDao = kundeDao;
	}

	public void loadKundeByPlannummer(int plannummer) {
		if (plannummer < 0) {
			throw new IllegalArgumentException("Plannummer muss >= 0 sein");
		}
		if (plannummer > 24) {
			throw new IllegalArgumentException("Plannummer muss <= 24 sein");
		}
		KundeEntity kunde = kundeDao.getKundeByPlanNr(plannummer);
		setKunde(kunde == null ? null : new Kunde(kunde));
	}

	/**
	 *  Methode zum Erhalt des einzigen KundeModel-Objekts.
	 *  Das Singleton-Pattern wird realisiert.
	 *  @return KundeModel, welches das einzige Objekt dieses
	 *          Typs ist.
	 */
	public static KundeModel getInstance() throws SQLException, ClassNotFoundException {
		if(kundeModel == null){
			kundeModel = new KundeModel(KundeDao.getInstance());
		}
		return kundeModel;	
	}
	
	/**
	 * gibt die Ueberschrift zum Grundfenster mit den Kundendaten heraus
	 * @return String, Ueberschrift zum Grundfenster mit den Kundendaten 
	 */
	public String getUeberschrift(){
		return "Verwaltung der Sonderwunschlisten";
	}
	
	/**
	 * gibt saemtliche Plannummern der Haeuser des Baugebiets heraus.
	 * @return ObservableList<Integer> , enthaelt saemtliche Plannummern der Haeuser
	 */
	public ObservableList<Integer> getPlannummern(){
		return this.plannummern;
	}
	// ---- Datenbankzugriffe -------------------
	
	/**
	 * speichert ein Kunde-Objekt in die Datenbank
	 * @param kunde, Kunde-Objekt, welches zu speichern ist
	 * @throws SQLException, Fehler beim Speichern in die Datenbank
	 */
	//TODO anschauen
	public void speichereKunden(Kunde kunde)
	    throws SQLException{
        // Speicherung des Kunden in der DB
   	    this.kunde = kunde;
		   kundeDao.speichereKundeByButton(kunde);
	}

	/**
	 * Löscht ein Kunde-Objekt aus der Datenbank
	 * @param planNr Int, welches zu löschen ist
	 * @throws SQLException, Fehler beim Speichern in die Datenbank
	 */

	public void loescheKunde(int planNr)
			throws SQLException{
		kundeDao.loescheKundeByButton(planNr);
	}

	public void aendereKunden(Kunde kunde) throws SQLException {
		kundeDao.aendereKunden(kunde);
	}
}
