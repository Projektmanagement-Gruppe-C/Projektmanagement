package gui.grundriss;

import business.fenster_aussentuer.FensterAussentuerModel;
import business.grundriss.GrundrissModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import gui.fenster_aussentuer.FensterAussentuerView;
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

	private KundeModel kundeModel;


	public GrundrissControl() throws Exception {
		Stage stageAussenanlage = new Stage();
		stageAussenanlage.initModality(Modality.APPLICATION_MODAL);
		this.model = model.getInstance();
		this.view = new GrundrissView(this, stageAussenanlage, this.model);
		this.kundeModel = KundeModel.getInstance();
		this.model.addPropertyChangeListener(this);
	}

	public void oeffneGrundrissView(){
		this.view.oeffneGrundrissView();
	}


	public boolean hatDachgeschoss() {
		switch (kundeModel.getKunde().getPlannummer()){
			case 1:
			case 6:
			case 7:
			case 14:
			case 15:
			case 24:
				System.out.println("Kein Dach");
				return false;
			default:
				System.out.println("Ein Dach");
				return true;
		}

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
