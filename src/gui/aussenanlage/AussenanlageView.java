package gui.aussenanlage;

import business.aussenanlage.Aussenanlage;
import business.aussenanlage.AussenanlageModel;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class AussenanlageView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private AussenanlageControl aussenanlageControl;
    private AussenanlageModel aussenanlageModel;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lblTerasse = new Label("Abstellraum auf der Terrasse des EG");
    private Label lblElAntriebEG = new Label("Vorbereitung fuer elektrische Antriebe Markise EG");
    private Label lblElAntriebDG = new Label("Vorbereitung fuer elektrische Antriebe Markise DG");
    private Label lblElMarkiseEG = new Label("Elektrische Markise EG");
    private Label lblElMarkiseDG = new Label("Elektrische Markise DG");
    private Label lblElGaragenTor = new Label("Eleketrischen Antrieb fuer das Garagentor");
    private Label lblSektionalGarage = new Label("Sektionaltor anstatt Schwingtor fuer die Garage");

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

    public AussenanlageView(AussenanlageControl aussenanlageControl, Stage aussenanlageStage,AussenanlageModel aussenanlageModel) throws SQLException, ClassNotFoundException {
        super(aussenanlageStage);
        this.aussenanlageControl = aussenanlageControl;
        aussenanlageStage.setTitle("Sonderwuensche zu Aussenanlage-Varianten");

       // this.aussenanlageControl.leseAussenanlageSonderwuensche();
        this.aussenanlageModel = aussenanlageModel;
        this.initKomponenten();
        setInhalt();
        getAussenanlageKunde();


    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = { lblTerasse, lblElAntriebEG, lblElAntriebDG,
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
        int gsmtPreis;
    }

    @Override
    protected void speichereSonderwuensche() {

    }

    /*schreibt die ausgesuchten Sonderwuensche in eine CSV-Datei */
    @Override
    protected void schreibeInCSV() {
        //TODO
        System.out.println("CSV Export_Aussenanlagen");
    }

    public void oeffneAussenanlageView() {
        super.oeffneBasisView();
    }

    protected void getAussenanlageKunde() throws SQLException, ClassNotFoundException {
        List<Integer> liste=aussenanlageControl.connectKunde();
        for(int i:liste){
            if(i==1)
                chckTerasse.setSelected(true);
            else if(i==2)
                chckElAntriebEG.setSelected(true);
            else if(i==3)
                chckElAntriebDG.setSelected(true);
            else if(i==4)
                chckElMarkiseEG.setSelected(true);
            else if(i==5)
                chckElMarkiseDG.setSelected(true);
            else if(i==6)
                chckElGaragenTor.setSelected(true);
            else if(i==7)
                chckSektionalGarage.setSelected(true);

        }

    }

    protected void setInhalt() {
        if (aussenanlageModel != null) {
            List<Aussenanlage> la = aussenanlageModel.loadAussenanlagen();
            txtTerasse.setText(la.get(0).getPreis() + "");
            txtElAntriebEG.setText(la.get(1).getPreis() + "");
            txtElAntriebDG.setText(la.get(2).getPreis() + "");
            txtElMarkiseEG.setText(la.get(3).getPreis() + "");
            txtElMarkiseDG.setText(la.get(4).getPreis() + "");
            txtElGaragenTor.setText(la.get(5).getPreis() + "");
            txtSektionalGarage.setText(la.get(6).getPreis() + "");

        }
    }
}




