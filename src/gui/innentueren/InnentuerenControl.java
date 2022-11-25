package gui.innentueren;

import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Innentueren-Varianten
 * kontrolliert.
 */
public final class InnentuerenControl {
	
	// das View-Objekt des Grundriss-Fensters
	private final InnentuerenView innentuerenView;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zu den Innentueren.
	 * @param innentuerenStage, Stage fuer das View-Objekt zu den Sonderwuenschen zu den Innentueren
	 */
	public InnentuerenControl( ){

	   	Stage stageGrundriss = new Stage();
    	stageGrundriss.initModality(Modality.APPLICATION_MODAL);
    	this.innentuerenView = new InnentuerenView(this, stageGrundriss);
	}
	    
	/**
	 * macht das InnentuerenView-Objekt sichtbar.
	 */
	public void oeffneInnentuerenView(){ 
		this.innentuerenView.oeffneInnentuerenView();
	}

	public void leseInnentuerenSonderwuensche(){
    } 
	
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	}
}
