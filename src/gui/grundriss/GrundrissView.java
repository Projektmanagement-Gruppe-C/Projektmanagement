package gui.grundriss;

import business.grundriss.GrundrissModel;
import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu 
 * den Grundrissvarianten bereitstellt.
 */
public class GrundrissView extends BasisView{

	// das Control-Objekt des Grundriss-Fensters
	private final GrundrissControl grundrissControl;
	private final GrundrissModel grundrissModel;

	//---Anfang Attribute der grafischen Oberflaeche---
	private final Label lblWandKueche
			= new Label("Wand zur Abtrennung der Küche von dem Essbereich");
	private final TextField txtPreisWandKueche 	= new TextField();
	private final Label lblWandKuecheEuro 		= new Label("Euro");
	private final CheckBox chckBxWandKueche 		= new CheckBox();

	private final Label lblTurKueche = new Label("T�r in der Wand zwischen K�che und Essbereich");
	private final TextField txtTurKueche 	= new TextField();
	private final Label lblTurKuecheEuro 		= new Label("Euro");
	private final CheckBox chckBxTurKueche 		= new CheckBox();

	private final Label lblGrossZimmer = new Label("Gro�es Zimmer im OG statt zwei kleine Zimmern");
	private final TextField txtGrossZimmer 	= new TextField();
	private final Label lblGrossZimmerEuro		= new Label("Euro");
	private final CheckBox chckBxGrossZimmer 		= new CheckBox();

	private final Label lblAbtTreppen = new Label("Abgetrennter Treppenraum im DG");
	private final TextField txtAbtTreppen 	= new TextField();
	private final Label lblAbtTreppenEuro		= new Label("Euro");
	private final CheckBox chckBxAbtTreppen		= new CheckBox();

	private final Label lblVorBad = new Label("Vorrichtung eines Bades im DG");
	private final TextField txtVorBad 	= new TextField();
	private final Label lblVorBadEuro		= new Label("Euro");
	private final CheckBox chckBxVorBad 		= new CheckBox();

	private final Label lblAusBad = new Label("Ausf�hrung eines Bades im DG");
	private final TextField txtAusBad 	= new TextField();
	private final Label lblAusBadEuro		= new Label("Euro");
	private final CheckBox chckBxAusBad 		= new CheckBox();

	private final Label lblGesamtP = new Label("Gesamtpreis");
	private final TextField txtGesamtP 	= new TextField();
	private final Label lblGesamtPEuro		= new Label("Euro");

	private TextField[] txtFieldArray = new TextField[6];
	private CheckBox[] checkBoxArray = new CheckBox[6];

	// Sonderwuensche
	private int[] ausgewaehlteSw = new int[6];

	private Label[] lblArray = {lblWandKueche, lblWandKuecheEuro, lblTurKueche, lblTurKuecheEuro, lblGrossZimmer,
			lblGrossZimmerEuro, lblAbtTreppen, lblAbtTreppenEuro,lblVorBad, lblVorBadEuro, lblAusBad, lblAusBadEuro,
			lblGesamtP, lblGesamtPEuro};
	//-------Ende Attribute der grafischen Oberflaeche-------

	/**
	 * erzeugt ein GrundrissView-Objekt, belegt das zugehoerige Control
	 * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
	 * @param grundrissControl GrundrissControl, enthaelt das zugehoerige Control
	 * @param grundrissStage Stage, enthaelt das Stage-Objekt fuer diese View
	 */
	public GrundrissView (GrundrissControl grundrissControl, GrundrissModel grundrissModel,Stage grundrissStage){
		super(grundrissStage);
		this.grundrissControl = grundrissControl;
		this.grundrissModel = grundrissModel;
		grundrissStage.setTitle("Sonderwünsche zu Grundriss-Varianten");

		this.initKomponenten();
		this.leseGrundrissSonderwuensche();
	}

	/* initialisiert die Steuerelemente auf der Maske */
	protected void initKomponenten(){
		super.initKomponenten();

		super.getLblSonderwunsch().setText("Grundriss-Varianten");
		super.getGridPaneSonderwunsch().add(lblWandKueche, 0, 1);
		super.getGridPaneSonderwunsch().add(txtPreisWandKueche, 1, 1);
		txtPreisWandKueche.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblWandKuecheEuro, 2, 1);
		super.getGridPaneSonderwunsch().add(chckBxWandKueche, 3, 1);

		super.getGridPaneSonderwunsch().add(lblTurKueche, 0, 2);
		super.getGridPaneSonderwunsch().add(txtTurKueche, 1, 2);
		txtTurKueche.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblTurKuecheEuro, 2, 2);
		super.getGridPaneSonderwunsch().add(chckBxTurKueche, 3, 2);

		super.getGridPaneSonderwunsch().add(lblGrossZimmer, 0, 3);
		super.getGridPaneSonderwunsch().add(txtGrossZimmer, 1, 3);
		txtGrossZimmer.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblGrossZimmerEuro, 2, 3);
		super.getGridPaneSonderwunsch().add(chckBxGrossZimmer, 3, 3);

		super.getGridPaneSonderwunsch().add(lblAbtTreppen, 0, 4);
		super.getGridPaneSonderwunsch().add(txtAbtTreppen, 1, 4);
		txtAbtTreppen.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblAbtTreppenEuro, 2, 4);
		super.getGridPaneSonderwunsch().add(chckBxAbtTreppen, 3, 4);

		super.getGridPaneSonderwunsch().add(lblVorBad, 0, 5);
		super.getGridPaneSonderwunsch().add(txtVorBad, 1, 5);
		txtVorBad.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblVorBadEuro, 2, 5);
		super.getGridPaneSonderwunsch().add(chckBxVorBad, 3, 5);

		super.getGridPaneSonderwunsch().add(lblAusBad, 0, 6);
		super.getGridPaneSonderwunsch().add(txtAusBad, 1, 6);
		txtAusBad.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblAusBadEuro, 2, 6);
		super.getGridPaneSonderwunsch().add(chckBxAusBad, 3, 6);

		super.getGridPaneSonderwunsch().add(lblGesamtP, 0, 8);
		super.getGridPaneSonderwunsch().add(txtGesamtP, 1, 8);
		txtGesamtP.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblGesamtPEuro, 2, 8);

	}

	/**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneGrundrissView(){
		super.oeffneBasisView();
	}

	private void leseGrundrissSonderwuensche(){
		this.grundrissControl.leseGrundrissSonderwuensche();
	}

	/* berechnet den Preis der ausgesuchten Sonderwuensche und zeigt diesen an */
	protected void berechneUndZeigePreisSonderwuensche(){
		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
		// aus dem Control aufgerufen, dann der Preis berechnet.
		Boolean konstellationIsAllowed =
				this.pruefeKonstellationSonderwuensche();
		if(!konstellationIsAllowed) {
			// Berechne Gesamtbetrag und gebe ihn aus
			System.out.println("Die Konstellation ist nicht korrekt!");
			return;
		}

		double gesamtBetrag = berechneGesamtBetrag();
		txtGesamtP.setText(Double.toString(gesamtBetrag));

		String output = "Ausgewaehlte Sonderwuensche: \n";
		for(int i = 0; i < ausgewaehlteSw.length; i++) {
			if(ausgewaehlteSw[i] == 1) {
				output += (i+1) + ". " + lblArray[i].getText() + "\n";
			}
		}
		JOptionPane.showMessageDialog(null, output);
	}

	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
	protected void speichereSonderwuensche(){
		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
		grundrissControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
		Boolean konstellationIsAllowed =
				this.pruefeKonstellationSonderwuensche();
		if(!konstellationIsAllowed) {
			System.out.println("Die Konstellation ist nicht erlaubt! Sonderwuensche koennen nicht gespeichert werden!");
			return;
		}

		for(int i = 0; i < ausgewaehlteSw.length; i++) {
			if(ausgewaehlteSw[i] == 1) {
				grundrissModel.setKundeSonderwunsch(i+1);
			}
		}

		JOptionPane.showMessageDialog(null, "Die Sonderwuensche wurden in der Datenbank gespeichert.");
	}

	/*schreibt die ausgesuchten Sonderwuensche in eine CSV-Datei */
	@Override
	protected void schreibeInCSV() {
		//TODO

		System.out.println("CSV Export_Grundriss");
	}

	public boolean pruefeKonstellationSonderwuensche() {
		for (int i = 0; i < checkBoxArray.length; i++) {
			ausgewaehlteSw[i] = 0;
			if (checkBoxArray[i].isSelected()) {
				ausgewaehlteSw[i] = 1;
			}
		}
		return grundrissControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
	}
	public double berechneGesamtBetrag() {
		double gesamtBetrag = 0;
		for(int i = 0; i < ausgewaehlteSw.length; i++) {
			if(ausgewaehlteSw[i] == 1) {
				gesamtBetrag += Double.parseDouble(txtFieldArray[i].getText());
			}
		}
		return gesamtBetrag;
	}

}