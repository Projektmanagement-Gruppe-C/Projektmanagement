package gui.aussenanlage;

import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AussenanlageView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private AussenanlageControl aussenanlageControl;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lblTerasse = new Label("Abstellraum auf der Terrasse des EG");
    private Label lblElAntriebEG = new Label("Vorbereitung f�r elektrische Antriebe Markise EG");
    private Label lblElAntriebDG = new Label("Vorbereitung f�r elektrische Antriebe Markise DG");
    private Label lblElMarkiseEG = new Label("Elektrische Markise EG");
    private Label lblElMarkiseDG = new Label("Elektrische Markise DG");
    private Label lblElGaragenTor = new Label("Eleketrischen Antrieb f�r das Garagentor");
    private Label lblSektionalGarage = new Label("Sektionaltor anstatt Schwingtor f�r die Garage");

    private TextField txtTerasse = new TextField();
    private TextField txtElAntriebEG = new TextField();
    private TextField txtElAntriebDG = new TextField();
    private TextField txtElMarkiseEG = new TextField();
    private TextField txtElMarkiseDG = new TextField();
    private TextField txtElGaragenTor = new TextField();
    private TextField txtSektionalGarage = new TextField();

    private CheckBox chckTerasse = new CheckBox();
    private CheckBox chckElAntriebEG = new CheckBox();
    private CheckBox chckElAntriebDG = new CheckBox();
    private CheckBox chckElMarkiseEG = new CheckBox();
    private CheckBox chckElMarkiseDG = new CheckBox();
    private CheckBox chckElGaragenTor = new CheckBox();
    private CheckBox chckSektionalGarage = new CheckBox();

    // -------Ende Attribute der grafischen Oberflaeche-------

    public AussenanlageView(AussenanlageControl aussenanlageControl, Stage aussenanlageStage) {
        super(aussenanlageStage);
        this.aussenanlageControl = aussenanlageControl;
        aussenanlageStage.setTitle("Sonderw�nsche zu Aussenanlage-Varianten");

        this.initKomponenten();
        this.aussenanlageControl.leseAussenanlageSonderwuensche();
    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = {lblTerasse, lblElAntriebEG, lblElAntriebDG,
                lblElMarkiseEG, lblElMarkiseDG, lblElGaragenTor, lblSektionalGarage};

        TextField[] txtFieldArray = {txtTerasse, txtElAntriebEG, txtElAntriebDG,
                txtElMarkiseEG, txtElMarkiseDG, txtElGaragenTor, txtSektionalGarage};

        CheckBox[] checkBoxArray = {chckTerasse, chckElAntriebEG, chckElAntriebDG,
                chckElMarkiseEG, chckElMarkiseDG, chckElGaragenTor, chckSektionalGarage};

        for (int i = 0; i < 7; i++) {

            super.getGridPaneSonderwunsch().add(lblSonderwunschArray[i], 0, (i + 1));
            super.getGridPaneSonderwunsch().add(txtFieldArray[i], 1, (i + 1));
            txtFieldArray[i].setEditable(false);
            super.getGridPaneSonderwunsch().add(new Label("Euro"), 2, (i + 1));
            super.getGridPaneSonderwunsch().add(checkBoxArray[i], 3, (i + 1));
        }

    }

    @Override
    protected void berechneUndZeigePreisSonderwuensche() {

    }

    @Override
    protected void speichereSonderwuensche() {

    }

    /*schreibt die ausgesuchten Sonderwuensche in eine CSV-Datei */
    @Override
    protected void schreibeInCSV() {
        this.aussenanlageControl.schreibeInCsv();
    }

    public void oeffneAussenanlageView() {
        super.oeffneBasisView();
    }
}

