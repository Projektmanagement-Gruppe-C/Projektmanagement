package gui.heizungen;


import business.heizungen.Heizungen;
import business.heizungen.HeizungenModel;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeizungenView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private HeizungenControl heizungenControl;
    private HeizungenModel heizungenModel;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lbl1 = new Label("Mehrpreis für einen zusätzlichen Standard-Heizkörper / je Stueck");
    private Label lbl2= new Label("Mehrpreis für einen Heizkörper mit glatter Oberfläche/ je Stueck");
    private Label lbl3 = new Label("Handtuchheizkörper / je Stück");
    private Label lbl4 = new Label("Fußbodenheizung ohne DG");
    private Label lbl5 = new Label("Fußbodenheizung mit DG");

    private TextField txt1= new TextField();
    private TextField txt2 = new TextField();
    private TextField txt3 = new TextField();
    private TextField txt4 = new TextField();
    private TextField txt5 = new TextField();


    private CheckBox chck1 = new CheckBox();
    private CheckBox chck2 = new CheckBox();
    private CheckBox chck3 = new CheckBox();
    private CheckBox chck4 = new CheckBox();
    private CheckBox chck5 = new CheckBox();



    // -------Ende Attribute der grafischen Oberflaeche-------

    public HeizungenView(HeizungenControl heizungenControl, Stage stage, HeizungenModel heizungenModel) throws SQLException, ClassNotFoundException {
        super(stage);
        this.heizungenControl = heizungenControl;
        stage.setTitle("Sonderwuensche zu Heizungen-Varianten");

        this.heizungenModel = heizungenModel;
        this.initKomponenten();
        setInhalt();
        getHeizungenKunde();
    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = { lbl1,lbl2,lbl3,lbl4,lbl5};

        TextField[] txtFieldArray = { txt1,txt2,txt3,txt4,txt5};

        CheckBox[] checkBoxArray = {chck1, chck2,chck3,chck4,chck5};

        for (int i = 0; i < 5; i++) {

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
        heizungenControl.loescheSonderwuensche();
        for(int i : list)
        heizungenControl.speichereSonderwunsch(i);
    }

    @Override
    protected void schreibeInCSV() {

    }

    public void oeffneHeizungenView() {
        super.oeffneBasisView();
    }

    protected void getHeizungenKunde() throws SQLException, ClassNotFoundException {
        List<Integer> liste= heizungenControl.connectKunde();
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

        }

    }

    protected void setInhalt() {
        if (heizungenModel != null) {
            List<Heizungen> la = heizungenModel.laodHeizungen();
            txt1.setText(la.get(0).getPreis() + "");
            txt2.setText(la.get(1).getPreis() + "");
            txt3.setText(la.get(2).getPreis() + "");
            txt4.setText(la.get(3).getPreis() + "");
            txt5.setText(la.get(4).getPreis() + "");
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
        return list;
    }


}




