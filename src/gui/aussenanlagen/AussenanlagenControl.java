package gui.aussenanlagen;

import business.kunde.KundeModel;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Aussenanlagen-Varianten
 * kontrolliert.
 */
public final class AussenanlagenControl {

	// das View-Objekt des Aussenanlagen-Fensters
	private final AussenanlagenView aussenanlagenView;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum
	 * Fenster fuer die Sonderwuensche zu den Aussenanlagen.
	 * @param aussenanlagenStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Aussenanlagen
	 */
	public AussenanlagenControl(KundeModel kundeModel){
		Stage stageAussenanlagen = new Stage();
		stageAussenanlagen.initModality(Modality.APPLICATION_MODAL);
		this.aussenanlagenView = new AussenanlagenView(this, stageAussenanlagen);
	}

	/**
	 * macht das AussenanlagenView-Objekt sichtbar.
	 */
	public void oeffneAussenanlagenView(){
		this.aussenanlagenView.oeffneAussenanlagenView();
	}

	public void leseAussenanlagenSonderwuensche(){
	}

	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	}
}
