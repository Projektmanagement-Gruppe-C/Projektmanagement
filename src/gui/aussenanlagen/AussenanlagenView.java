package gui.aussenanlagen;

		import business.kunde.KundeModel;
		import gui.basis.BasisView;
		import javafx.scene.control.CheckBox;
		import javafx.scene.control.Label;
		import javafx.scene.control.TextField;
		import javafx.stage.Modality;
		import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu
 * den Aussenanlagenvarianten bereitstellt.
 */
public class AussenanlagenView extends BasisView {

	// das Control-Objekt des Aussenanlagen-Fensters
	private final AussenanlagenControl aussenanlagenControl;

	//---Anfang Attribute der grafischen Oberflaeche---
	private final Label lblAbstellraumTerrasse
			= new Label("Abstellraum auf der Terrasse des EG");
	private final TextField txtPreisAbstellraumTerrasse 	= new TextField();
	private final Label lblAbstellraumTerrasseEuro 		= new Label("Euro");
	private final CheckBox chckBxAbstellraumTerrasse		= new CheckBox();

	private final Label lblVorMarkiseEG = new Label("Vorbereitung für elektrische Antriebe Markise EG");
	private final TextField txtVorMarkiseEG 	= new TextField();
	private final Label lblVorMarkiseEGEuro 		= new Label("Euro");
	private final CheckBox chckBxVorMarkiseEG 		= new CheckBox();

	private final Label lblVorMarkiseDG = new Label("Vorbereitung für elektrische Antriebe Markise DG");
	private final TextField txtVorMarkiseDG 	= new TextField();
	private final Label lblVorMarkiseDGEuro		= new Label("Euro");
	private final CheckBox chckBxVorMarkiseDG 		= new CheckBox();

	private final Label lblMarkiseEG = new Label("Elektrische Markise EG");
	private final TextField txtMarkiseEG 	= new TextField();
	private final Label lblMarkiseEGEuro		= new Label("Euro");
	private final CheckBox chckBxMarkiseEG		= new CheckBox();

	private final Label lblMarkiseDG = new Label("Elektrische Markise DG");
	private final TextField txtMarkiseDG 	= new TextField();
	private final Label lblMarkiseDGEuro		= new Label("Euro");
	private final CheckBox chckBxMarkiseDG 		= new CheckBox();

	private final Label lblElGaragentor = new Label("Elektrischen Antrieb für das Garagentor");
	private final TextField txtElGaragentor 	= new TextField();
	private final Label lblElGaragentorEuro		= new Label("Euro");
	private final CheckBox chckBxElGaragentor 		= new CheckBox();

	private final Label lblSekGaragentor = new Label("Sektionaltor anstatt Schwingtor für die Garage");
	private final TextField txtSekGaragentor 	= new TextField();
	private final Label lblSekGaragentorEuro		= new Label("Euro");
	private final CheckBox chckBxSekGaragentor 		= new CheckBox();

	private final Label lblGesamtP = new Label("Gesamtpreis");
	private final TextField txtGesamtP 	= new TextField();
	private final Label lblGesamtPEuro		= new Label("Euro");


	//-------Ende Attribute der grafischen Oberflaeche-------

	/**
	 * erzeugt ein AussenanlagenView-Objekt, belegt das zugehoerige Control
	 * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
	 * @param aussenanlagenControl AussenanlagenControl, enthaelt das zugehoerige Control
	 * @param aussenanlagenStage Stage, enthaelt das Stage-Objekt fuer diese View
	 */
	public AussenanlagenView(AussenanlagenControl aussenanlagenControl, Stage aussenanlagenStage){
		super(aussenanlagenStage);
		this.aussenanlagenControl = aussenanlagenControl;
		aussenanlagenStage.setTitle("Sonderwünsche zu Aussenanlagen-Varianten");

		this.initKomponenten();
		this.leseAussenanlagenSonderwuensche();
	}

	/* initialisiert die Steuerelemente auf der Maske */
	protected void initKomponenten(){
		super.initKomponenten();

		super.getLblSonderwunsch().setText("Aussenanlagen-Varianten");
		super.getGridPaneSonderwunsch().add(lblAbstellraumTerrasse, 0, 1);
		super.getGridPaneSonderwunsch().add(txtPreisAbstellraumTerrasse, 1, 1);
		txtPreisAbstellraumTerrasse.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblAbstellraumTerrasseEuro, 2, 1);
		super.getGridPaneSonderwunsch().add(chckBxAbstellraumTerrasse, 3, 1);

		super.getGridPaneSonderwunsch().add(lblVorMarkiseEG, 0, 2);
		super.getGridPaneSonderwunsch().add(txtVorMarkiseEG, 1, 2);
		txtVorMarkiseEG.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblVorMarkiseEGEuro, 2, 2);
		super.getGridPaneSonderwunsch().add(chckBxVorMarkiseEG, 3, 2);

		super.getGridPaneSonderwunsch().add(lblVorMarkiseDG, 0, 3);
		super.getGridPaneSonderwunsch().add(txtVorMarkiseDG, 1, 3);
		txtVorMarkiseDG.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblVorMarkiseDGEuro, 2, 3);
		super.getGridPaneSonderwunsch().add(chckBxVorMarkiseDG, 3, 3);

		super.getGridPaneSonderwunsch().add(lblMarkiseEG, 0, 4);
		super.getGridPaneSonderwunsch().add(txtMarkiseEG, 1, 4);
		txtMarkiseEG.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblMarkiseEGEuro, 2, 4);
		super.getGridPaneSonderwunsch().add(chckBxMarkiseEG, 3, 4);

		super.getGridPaneSonderwunsch().add(lblMarkiseDG, 0, 5);
		super.getGridPaneSonderwunsch().add(txtMarkiseDG, 1, 5);
		txtMarkiseDG.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblMarkiseDGEuro, 2, 5);
		super.getGridPaneSonderwunsch().add(chckBxMarkiseDG, 3, 5);

		super.getGridPaneSonderwunsch().add(lblElGaragentor, 0, 6);
		super.getGridPaneSonderwunsch().add(txtElGaragentor, 1, 6);
		txtElGaragentor.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblElGaragentorEuro, 2, 6);
		super.getGridPaneSonderwunsch().add(chckBxElGaragentor, 3, 6);

		super.getGridPaneSonderwunsch().add(lblSekGaragentor, 0, 7);
		super.getGridPaneSonderwunsch().add(txtSekGaragentor, 1, 7);
		txtSekGaragentor.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblSekGaragentorEuro, 2, 7);
		super.getGridPaneSonderwunsch().add(chckBxSekGaragentor, 3, 7);

		super.getGridPaneSonderwunsch().add(lblGesamtP, 0, 9);
		super.getGridPaneSonderwunsch().add(txtGesamtP, 1, 9);
		txtGesamtP.setEditable(false);
		super.getGridPaneSonderwunsch().add(lblGesamtPEuro, 2, 9);

	}

	/**
	 * macht das AussenanlagenView-Objekt sichtbar.
	 */
	public void oeffneAussenanlagenView(){
		super.oeffneBasisView();
	}

	private void leseAussenanlagenSonderwuensche(){
		this.aussenanlagenControl.leseAussenanlagenSonderwuensche();
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


}