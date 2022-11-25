package gui.fensterAussentuere;


import java.util.ArrayList;
import java.util.List;


import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage; 

public class FensterTuereControl 
{
	private FensterTuereView view;
	

	public FensterTuereControl(KundeModel kundeModel) {
		Stage stageFensterTuere = new Stage();
		stageFensterTuere.initModality(Modality.APPLICATION_MODAL);
		this.view = new FensterTuereView(this, stageFensterTuere);
		
	}
	
	public void oeffneFensterTuereView() {
		this.view.oeffneFensterTuereView();
	}
	
	public List<Integer> leseSanitaerinstallationSonderwuensche() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		// TODO Markierte Sonderwuensche auslesen
		return arrayList;
	}

	public boolean pruefeKonstellationSonderwuensche(List<Integer> sw) {
		return true;
		// TODO Ueberpruefung fehlt noch
	}

	public void speichereKonstellation(List<Integer> ausgewaehlteWuensche) {
		//model.speichereFensterTuere(ausgewaehlteWuensche);
		// TODO Markierte Sonderwuensche abspeichern
	}
}
