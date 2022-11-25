package gui.kunde;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import business.kunde.Kunde;
import business.kunde.KundeModel;
import gui.fensterAussentuere.FensterTuereControl;
import gui.aussenanlage.AussenanlageControl;
import gui.grundriss.GrundrissControl;
import gui.innentueren.InnentuerenControl;
import javafx.stage.Stage;

/**
 * Klasse, welche das Grundfenster mit den Kundendaten kontrolliert.
 */
public class KundeControl implements PropertyChangeListener {
       
   ;
	// das View-Objekt des Grundfensters mit den Kundendaten
	  private final KundeView kundeView;
    // das Model-Objekt des Grundfensters mit den Kundendaten
    private final KundeModel kundeModel;
    /* das GrundrissControl-Objekt fuer die Sonderwuensche
       zum Grundriss zu dem Kunden */
    private GrundrissControl grundrissControl;

    /* das InnentuerenControl-Objekt fuer die Sonderwuensche
    zu den Innentueren zu dem Kunden */
    private InnentuerenControl innentuerenControl;

	  private AussenanlageControl aussenanlageControl;
	  
	  private FensterTuereControl fensterTuereControl;

    
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
    public void oeffneGrundrissControl(){
    	if (this.grundrissControl == null){
    		this.grundrissControl = new GrundrissControl(kundeModel);
      	}
    	this.grundrissControl.oeffneGrundrissView();
    }

    /*
     * erstellt, falls nicht vorhanden, ein Innentueren-Control-Objekt.
     * Das InnentuerenView wird sichtbar gemacht.
     */
    public void oeffneInnentuerenControl() {
    	if(this.innentuerenControl == null) {
    		this.innentuerenControl = new InnentuerenControl(kundeModel);
    	}
    	this.innentuerenControl.oeffneInnentuerenView();
    }
    


	  public void oeffneAussenanlageControl(){
		  if(this.aussenanlageControl == null){
			  this.aussenanlageControl = new AussenanlageControl();
		  }
		  this.aussenanlageControl.oeffneAussenanlageView();
	  }

	/**
	 * speichert ein Kunde-Objekt in die Datenbank
	 * @param kunde, Kunde-Objekt, welches zu speichern ist
	 */
    public void speichereKunden(Kunde kunde){
      	try{
    		kundeModel.speichereKunden(kunde);
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

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String propertyName = e.getPropertyName();
		if (propertyName.equals(KundeModel.KUNDE_PROPERTY)){
			Kunde kunde = (Kunde) e.getNewValue();
			this.kundeView.setKundeDaten(kunde);
		}
	}

	public void oeffneFensterTuereView() {
		if (this.fensterTuereControl == null) {
			this.fensterTuereControl = new FensterTuereControl(kundeModel);
		}
		this.fensterTuereControl.oeffneFensterTuereView();
	}
}
