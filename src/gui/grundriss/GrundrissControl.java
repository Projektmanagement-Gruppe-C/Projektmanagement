package gui.grundriss;

import business.grundriss.GrundrissModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Grundriss-Varianten
 * kontrolliert.
 */
public final class GrundrissControl implements PropertyChangeListener {

	// das View-Objekt des Grundriss-Fensters
	private GrundrissView view;

	private GrundrissModel model;

	private final GrundrissModel grundrissModel;

	private final int kundeId;

	private KundeModel kundeModel;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 */
	public GrundrissControl (int kundeID) throws SQLException, ClassNotFoundException {
		this.kundeId = kundeID;
		this.kundeModel = KundeModel.getInstance();
	   	Stage stageGrundriss = new Stage();
    	stageGrundriss.initModality(Modality.APPLICATION_MODAL);
		this.grundrissModel = GrundrissModel.getInstance();
    	this.grundrissView = new GrundrissView(this, stageGrundriss, this.model);
		this.model.addPropertyChangeListener(this);
	}

	public void oeffneGrundrissView(){
		this.view.oeffneGrundrissView();
	}

	public void leseGrundrissSonderwuensche(){
		grundrissModel.loadKundenData(kundeId);
    }
	

	public void hatDachgeschoss() {
		//TODO

	}

	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		//TODO
		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if (propertyName.equals(GrundrissModel.GRUNDRISS_PROPERTY)){

		}
	}

	public void test() throws SQLException, ClassNotFoundException {
		List<Integer> la = connectKunde();
		Kunde kunde = kundeModel.getInstance().getKunde();
	}

	public List<Integer> connectKunde() throws SQLException, ClassNotFoundException {
		Kunde kunde= kundeModel.getInstance().getKunde();
		List<Integer>la = model.loadGrundrissnListe(kunde.getKundennummer());
		return la;
	}

	public void speichereSonderwunsch(int sid) throws SQLException, ClassNotFoundException {
		int kid = kundeModel.getKunde().getKundennummer();
		List<Integer> li = connectKunde();
		if(!li.contains(sid))
			model.speichereSonderwuensche(sid,kid);
	}

	public void loescheSonderwuensche() throws SQLException {
		int kid = kundeModel.getKunde().getKundennummer();
		model.loescheSonderwuensche(kid);
	}
}
