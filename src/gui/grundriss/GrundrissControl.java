package gui.grundriss;

import business.grundriss.GrundrissModel;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Grundriss-Varianten
 * kontrolliert.
 */
public final class GrundrissControl {
	
	// das View-Objekt des Grundriss-Fensters
	private final GrundrissView grundrissView;

	private final GrundrissModel grundrissModel;

	private final int kundeId;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 */
	public GrundrissControl(int kundeID) throws SQLException, ClassNotFoundException {
		this.kundeId = kundeID;
	   	Stage stageGrundriss = new Stage();
    	stageGrundriss.initModality(Modality.APPLICATION_MODAL);
		this.grundrissModel = GrundrissModel.getInstance();
    	this.grundrissView = new GrundrissView(this, this.grundrissModel, stageGrundriss);
	}

	/**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneGrundrissView(){ 
		this.grundrissView.oeffneGrundrissView();
	}

	public void leseGrundrissSonderwuensche(){
		grundrissModel.loadKundenData(kundeId);
    } 
	
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	}
	
}
