package gui.grundriss;

import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Grundriss-Varianten
 * kontrolliert.
 */
public final class GrundrissControl {
	
	// das View-Objekt des Grundriss-Fensters
	private GrundrissView grundrissView;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 * @param grundrissStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Grundriss
	 */
	public GrundrissControl(KundeModel kundeModel){  
	   	Stage stageGrundriss = new Stage();
    	stageGrundriss.initModality(Modality.APPLICATION_MODAL);
    	this.grundrissView = new GrundrissView(this, stageGrundriss);
	}
	    
	/**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneGrundrissView(){ 
		this.grundrissView.oeffneGrundrissView();
	}

	public void leseGrundrissSonderwuensche(){
    } 
	
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	}
}
