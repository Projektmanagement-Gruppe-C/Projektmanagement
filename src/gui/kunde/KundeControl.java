package gui.kunde;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import business.aussenanlage.AussenanlageModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import gui.aussenanlage.AussenanlageControl;
import gui.fenster_aussentuer.FensterAussentuerControl;
import gui.fliesen.FliesenControl;
import gui.grundriss.GrundrissControl;
import gui.heizungen.HeizungenControl;
import gui.innentueren.InnentuerenControl;
import gui.parkett.ParkettControl;
import gui.sanitaer.SanitaerControl;
import javafx.stage.Stage;

/**
 * Klasse, welche das Grundfenster mit den Kundendaten kontrolliert.
 */
public class KundeControl implements PropertyChangeListener {
       
    // das View-Objekt des Grundfensters mit den Kundendaten
	  private final KundeView kundeView;
    // das Model-Objekt des Grundfensters mit den Kundendaten
    private final KundeModel kundeModel;

    /* das Control-Objekt fuer die Sonderwuensche */
    private InnentuerenControl innentuerenControl;
	private AussenanlageControl aussenanlageControl;
	private FliesenControl fliesenControl;
	private ParkettControl parkettControl;
	private GrundrissControl grundrissControl;
	private FensterAussentuerControl fensterAussentuerControl;
	private SanitaerControl sanitaerControl;
	private HeizungenControl heizungenControl;


	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Grundfenster mit den Kundendaten.
	 * @param primaryStage, Stage fuer das View-Objekt zu dem Grundfenster mit den Kundendaten
	 */
    public KundeControl(Stage primaryStage) throws SQLException, ClassNotFoundException {
        this.kundeModel = KundeModel.getInstance();
		this.kundeModel.addPropertyChangeListener(this);
        this.kundeView = new KundeView(this, primaryStage, kundeModel);
    }
    
    /*
     * erstellt, falls nicht vorhanden, ein Grundriss-Control-Objekt.
     * Das GrundrissView wird sichtbar gemacht.
     */
    public void oeffneGrundrissControl() throws Exception {
    	if (this.grundrissControl == null){
    		this.grundrissControl = new GrundrissControl();
      	}
    	this.grundrissControl.oeffneGrundrissView();
    }

//     * erstellt, falls nicht vorhanden, ein  SOnderwunsch-Control-Objekt.
//     */
		  public void oeffneInnentuerenControl() throws Exception {
			  if (this.innentuerenControl == null) {
				  this.innentuerenControl = new InnentuerenControl();
			  }
			  this.innentuerenControl.oeffneInnentuerenView();
		  }

	  public void oeffneAussenanlageControl() throws Exception {
		  if(this.aussenanlageControl == null){
			  this.aussenanlageControl = new AussenanlageControl();
		  }
		  this.aussenanlageControl.oeffneAussenanlageView();
	  }

	  public void oeffneFensterAussentuerControl() throws Exception {
		  if(this.fensterAussentuerControl == null){
			  this.fensterAussentuerControl = new FensterAussentuerControl();
		  }
		  this.fensterAussentuerControl.oeffneFensterAussentuerView();
	  }

	public void oeffneParkettControl() throws Exception {
		if(this.parkettControl == null){
			this.parkettControl = new ParkettControl();
		}
		this.parkettControl.oeffneParkettView();
	}

	public void oeffneFliesenControl() throws Exception {
		if(this.fliesenControl == null){
			this.fliesenControl = new FliesenControl();
		}
		this.fliesenControl.oeffneFliesenView();
	}

	public void oeffneSanitaerControl() throws Exception {
		if(this.sanitaerControl == null){
			this.sanitaerControl = new SanitaerControl();
		}
		this.sanitaerControl.oeffneSanitaerView();
	}

	public void oeffneHeizungenControl() throws Exception {
		if(this.heizungenControl == null){
			this.heizungenControl = new HeizungenControl();
		}
		this.heizungenControl.oeffneHeizungenView();
	}

	/**
	 * speichert ein Kunde-Objekt in die Datenbank
	 * @param kunde, Kunde-Objekt, welches zu speichern ist
	 */
    public void speichereKunden(Kunde kunde){
      	try{
			  if(kunde.istValide()) {
				  kundeModel.speichereKunden(kunde);
				  kundeView.zeigeMeldung("Speichern erfolgreich","Das Speichern war ein voller Erfolg");

			  }
			  else{
				  this.kundeView.zeigeFehlermeldung("Daten fehler", "Die eingeben Daten bitte überarbeiten ");
			  }
    	}


    	catch(SQLException exc){
    		exc.printStackTrace();
    		this.kundeView.zeigeFehlermeldung("SQLException",
                "Fehler beim Speichern in die Datenbank");
    	}
    	catch(Exception exc){
    		exc.printStackTrace();
    		this.kundeView.zeigeFehlermeldung("Exception",
                "Unbekannter Fehler");
    	}
    }
	public void loadKundeByPlannummer(int plannummer) {
		kundeModel.loadKundeByPlannummer(plannummer);
	}
	/**
	 * LÖSCHT ein Kunde-Objekt in die Datenbank
	 * @param planNr, int, welches zu löschen ist
	 */
	public void loescheKunde(int planNr){
		try{
			kundeModel.loescheKunde(planNr);
			kundeView.zeigeMeldung("Löschen erfolgreich","Das Löschen war ein voller Erfolg");

		}
		catch(SQLException exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("SQLException",
					"Fehler beim Löschen in die Datenbank");
		}
		catch(Exception exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("Exception",
					"Unbekannter Fehler");
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String propertyName = e.getPropertyName();
		if (propertyName.equals(KundeModel.KUNDE_PROPERTY)){
			Kunde kunde = (Kunde) e.getNewValue();
			this.kundeView.setKundeDaten(kunde);
		}
	}

	public void aendereKunden(Kunde kunde) {
		try{
			kundeModel.aendereKunden(kunde);
			kundeView.zeigeMeldung("Ändern erfolgreich","Das Anpassen war ein voller Erfolg");

		}
		catch(SQLException exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("SQLException",
					"Fehler beim Speichern in die Datenbank");
		}
		catch(Exception exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("Exception",
					"Unbekannter Fehler");
		}
	}
}
