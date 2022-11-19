package gui.innentueren;

import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu 
 * den Grundrissvarianten bereitstellt.
 */
public class InnentuerenView extends BasisView{
 
 	// das Control-Objekt des Innentueren-Fensters
	private final InnentuerenControl innentuerenControl;
   
    //---Anfang Attribute der grafischen Oberflaeche---
    private final Label lblKlarglas
        = new Label("Mehrpreis für die Ausführung eines Glasausschnitts"
        		+ "(Klarglas) in einer Innentür:");
    private final TextField txtPreisKlarglas 	= new TextField();
    private final Label lblKlarglasEuro 		= new Label("Euro pro Tuer");
    private final TextField anzahlKlarglas      = new TextField("0");
   
    private final Label lblMilchglas = new Label("Mehrpreis für die Ausführung eines Glasausschnitts"
    		+ "(Milchglas) in einer Innentür:");
    private final TextField txtPreisMilchglas 	= new TextField();
    private final Label lblMilchglasEuro 		= new Label("Euro pro Tuer");
    private final TextField anzahlMilchglas     = new TextField("0");
    
    private final Label lblGarageHolz = new Label("Innentür zur Garage als Holztür:");
    private final TextField txtPreisGarageHolz 	= new TextField();
    private final Label lblGarageHolzEuro		= new Label("Euro");
    private final CheckBox chckBxGarageHolz		= new CheckBox();
    
    
    private final Label lblGesamtP = new Label("Gesamtpreis");
    private final TextField txtGesamtP 	= new TextField();
    private final Label lblGesamtPEuro		= new Label("Euro");

    
    //-------Ende Attribute der grafischen Oberflaeche-------
  
    /**
     * erzeugt ein InnentuerenView-Objekt, belegt das zugehoerige Control
     * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
     * @param innentuerenControl InnentuerenControl, enthaelt das zugehoerige Control
     * @param innentuerenStage Stage, enthaelt das Stage-Objekt fuer diese View
     */
    public InnentuerenView (InnentuerenControl innentuerenControl, Stage innentuerenStage){
    	super(innentuerenStage);
        this.innentuerenControl = innentuerenControl;
        innentuerenStage.setTitle("SonderwÃ¼nsche zu den Innentueren");
                
	    this.initKomponenten();
	    this.leseInnentuerenSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(){
    	super.initKomponenten(); 
       	
    	super.getLblSonderwunsch().setText("Sonderwuensche Innentueren");
       	super.getGridPaneSonderwunsch().add(lblKlarglas, 0, 1);
    	super.getGridPaneSonderwunsch().add(txtPreisKlarglas, 1, 1);
    	txtPreisKlarglas.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblKlarglasEuro, 2, 1);
    	anzahlKlarglas.setPrefWidth(25);
    	super.getGridPaneSonderwunsch().add(anzahlKlarglas, 3, 1);
    	
    	
    	super.getGridPaneSonderwunsch().add(lblMilchglas, 0, 2);
    	super.getGridPaneSonderwunsch().add(txtPreisMilchglas, 1, 2);
    	txtPreisMilchglas.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblMilchglasEuro, 2, 2);
    	anzahlMilchglas.setPrefWidth(25);
    	super.getGridPaneSonderwunsch().add(anzahlMilchglas, 3, 2);
    	
    	super.getGridPaneSonderwunsch().add(lblGarageHolz, 0, 3);
    	super.getGridPaneSonderwunsch().add(txtPreisGarageHolz, 1, 3);
    	txtPreisGarageHolz.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblGarageHolzEuro, 2, 3);
    	super.getGridPaneSonderwunsch().add(chckBxGarageHolz, 3, 3);
    	
    	
    	super.getGridPaneSonderwunsch().add(lblGesamtP, 0, 5);
    	super.getGridPaneSonderwunsch().add(txtGesamtP, 1, 5);
    	txtGesamtP.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblGesamtPEuro, 2, 5);
    	
    }  
    
    /**
	 * macht das InnentuerenView-Objekt sichtbar.
	 */
	public void oeffneInnentuerenView(){ 
		super.oeffneBasisView();
	}
    
    private void leseInnentuerenSonderwuensche(){
    	this.innentuerenControl.leseInnentuerenSonderwuensche();
    }
    
 	/* berechnet den Preis der ausgesuchten Sonderwuensche und zeigt diesen an */
  	protected void berechneUndZeigePreisSonderwuensche(){
  		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.
  	}
  	
   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}

	/*schreibt die ausgesuchten Sonderwuensche in eine CSV-Datei */
	@Override
	protected void schreibeInCSV() {
		//TODO
		System.out.println("CSV Export_Innentüren");
	}


}


