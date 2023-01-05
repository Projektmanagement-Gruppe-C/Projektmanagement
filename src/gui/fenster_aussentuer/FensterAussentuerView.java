package gui.fenster_aussentuer;

import business.aussenanlage.Aussenanlage;
import business.aussenanlage.AussenanlageModel;
import business.fenster_aussentuer.FensterAussentuerModel;
import business.fenster_aussentuer.FenterAussentuer;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FensterAussentuerView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private FensterAussentuerControl fensterAussentuerControl;
    private FensterAussentuerModel fensterAussentuerModel;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lbl1 = new Label("Schiebetüren im EG zur Terrasse");
    private Label lbl2 = new Label("Schiebetüren im DG zur Dachterrasse");
    private Label lbl3 = new Label("Erhöhter Einbruchschutz an der Haustür");
    private Label lbl4 = new Label("Vorbereitung für elektrische Antriebe Rolläden EG");
    private Label lbl5 = new Label("Vorbereitung für elektrische Antriebe Rolläden OG");
    private Label lbl6 = new Label("Vorbereitung für elektrische Antriebe Rolläden DG");
    private Label lbl7 = new Label("Elektrische Rolläden EG");
    private Label lbl8 = new Label("Elektrische Rolläden OG");
    private Label lbl9 = new Label("Elektrische Rolläden DG");


    private TextField txt1 = new TextField();
    private TextField txt2 = new TextField();
    private TextField txt3 = new TextField();
    private TextField txt4 = new TextField();
    private TextField txt5 = new TextField();
    private TextField txt6 = new TextField();
    private TextField txt7 = new TextField();
    private TextField txt8 = new TextField();
    private TextField txt9 = new TextField();


    private CheckBox chck1 = new CheckBox();
    private CheckBox chck2 = new CheckBox();
    private CheckBox chck3 = new CheckBox();
    private CheckBox chck4 = new CheckBox();
    private CheckBox chck5 = new CheckBox();
    private CheckBox chck6 = new CheckBox();
    private CheckBox chck7 = new CheckBox();
    private CheckBox chck8 = new CheckBox();
    private CheckBox chck9 = new CheckBox();


    // -------Ende Attribute der grafischen Oberflaeche-------

    public FensterAussentuerView(FensterAussentuerControl fensterAussentuerControl, Stage stage, FensterAussentuerModel fensterAussentuerModel) throws SQLException, ClassNotFoundException {
        super(stage);
        this.fensterAussentuerControl = fensterAussentuerControl;
        stage.setTitle("Sonderwuensche zu Fenster_Aussentuer-Varianten");

       // this.aussenanlageControl.leseAussenanlageSonderwuensche();
        this.fensterAussentuerModel = fensterAussentuerModel;
        this.initKomponenten();
        setInhalt();
        getFensterAussentuerKunde();


    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = { lbl1, lbl2, lbl3 ,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9};

        TextField[] txtFieldArray = {txt1, txt2, txt3, txt4, txt5,txt6,txt7,txt8,txt9};

        CheckBox[] checkBoxArray = {chck1,chck2,chck3,chck4,chck5,chck6,chck7,chck8,chck9};

        for (int i = 0; i < 9; i++) {

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
    protected void speichereSonderwuensche() throws SQLException, ClassNotFoundException {
        List<Integer> list = getChcks();
        fensterAussentuerControl.loescheSonderwuensche();
        for(int i : list)
            fensterAussentuerControl.speichereSonderwunsch(i);
    }

    @Override
    protected void schreibeInCSV() {

    }

    public void oeffneFensterAussentuerView() {
        super.oeffneBasisView();
    }

    protected void getFensterAussentuerKunde() throws SQLException, ClassNotFoundException {
        List<Integer> liste= fensterAussentuerControl.connectKunde();
        for(int i:liste){
            if(i==1)
                chck1.setSelected(true);
            else if(i==2)
                chck2.setSelected(true);
            else if(i==3)
                chck3.setSelected(true);
            else if(i==4)
                chck4.setSelected(true);
            else if(i==5)
                chck5.setSelected(true);
            else if(i==6)
                chck6.setSelected(true);
            else if(i==7)
                chck7.setSelected(true);
            else if(i==8)
                chck8.setSelected(true);
            else if(i==9)
                chck9.setSelected(true);
        }

    }

    protected void setInhalt() {
        if (fensterAussentuerModel != null) {
            List<FenterAussentuer> la = fensterAussentuerModel.laodFensterAussentuer();
            txt1.setText(la.get(0).getPreis() + "");
            txt2.setText(la.get(1).getPreis() + "");
            txt3.setText(la.get(2).getPreis() + "");
            txt4.setText(la.get(3).getPreis() + "");
            txt5.setText(la.get(4).getPreis() + "");
            txt6.setText(la.get(5).getPreis() + "");
            txt7.setText(la.get(6).getPreis() + "");
            txt8.setText(la.get(7).getPreis() + "");
            txt9.setText(la.get(8).getPreis() + "");
        }
    }

    public List<Integer> getChcks() {
        List <Integer> list = new ArrayList<>();

        if(chck1.isSelected())
            list.add(1);
        if(chck2.isSelected())
            list.add(2);
        if(chck3.isSelected())
            list.add(3);
        if(chck4.isSelected())
            list.add(4);
        if(chck5.isSelected())
            list.add(5);
        if(chck6.isSelected())
            list.add(6);
        if(chck7.isSelected())
            list.add(7);
        if(chck8.isSelected())
            list.add(8);
        if(chck9.isSelected())
            list.add(9);



        return list;
    }


}




