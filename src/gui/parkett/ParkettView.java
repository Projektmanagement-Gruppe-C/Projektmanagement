package gui.parkett;

import business.fliesen.Fliesen;
import business.fliesen.FliesenModel;
import business.parkett.Parkett;
import business.parkett.ParkettModel;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkettView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private ParkettControl parkettControl;
    private ParkettModel parkettModel;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lblLEEG = new Label("Landhausdielen massiv im Essbereich des EG");
    private Label lblLKEG = new Label("Landhausdielen massiv im Küchenbereich des EG");
    private Label lblSEEG = new Label("Stäbchenparkett im Essbereich des EG");
    private Label lblSKEG = new Label("Stäbchenparkett im Küchenbereich des EG");
    private Label lblLOG = new Label("Landhausdielen massiv im OG");
    private Label lblSOG = new Label("Stäbchenparkett im OG");
    private Label lblLKDG = new Label("Landhausdielen massiv komplett im DG");
    private Label lblLOBDG = new Label("Landhausdielen massiv ohne Badbereich im DG");
    private Label lblSKDG = new Label("Stäbchenparkett im DG komplett im DG");
    private Label lblSOBDG = new Label("Stäbchenparkett ohne Badbereich im DG\t");

    private TextField txtLEEG= new TextField();
    private TextField txtLKEG= new TextField();
    private TextField txtSEEG= new TextField();
    private TextField txtSKEG= new TextField();
    private TextField txtLOG= new TextField();
    private TextField txtSOG= new TextField();
    private TextField txtLKDG= new TextField();
    private TextField txtLOBDG= new TextField();
    private TextField txtSKDG= new TextField();
    private TextField txtSOBDG= new TextField();


    private CheckBox chckLEEG = new CheckBox();
    private CheckBox chckLKEG = new CheckBox();
    private CheckBox chckSEEG = new CheckBox();
    private CheckBox chckSKEG = new CheckBox();
    private CheckBox chckLOG = new CheckBox();
    private CheckBox chckSOG = new CheckBox();
    private CheckBox chckLKDG = new CheckBox();
    private CheckBox chckLOBDG = new CheckBox();
    private CheckBox chckSKDG = new CheckBox();
    private CheckBox chckSOBDG = new CheckBox();


    // -------Ende Attribute der grafischen Oberflaeche-------

    public ParkettView(ParkettControl parkettControl, Stage parkettStage, ParkettModel parkettModel) throws SQLException, ClassNotFoundException {
        super(parkettStage);
        this.parkettControl = parkettControl;
        parkettStage.setTitle("Sonderwuensche zu Fliesen-Varianten");

       // this.fliesenControl.leseFliesenSonderwuensche();
        this.parkettModel = parkettModel;
        this.initKomponenten();
        setInhalt();
        getParkettKunde();
    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = { lblLEEG,lblLKEG,lblSEEG,lblSKEG,lblLOG,lblSOG,lblLKDG,lblLOBDG,lblSKDG,lblSOBDG};

        TextField[] txtFieldArray = {txtLEEG,txtLKEG,txtSEEG,txtSKEG, txtLOG,txtSOG,txtLKDG,txtLOBDG,txtSKDG,txtSOBDG};

        CheckBox[] checkBoxArray = { chckLEEG,chckLKEG,chckSEEG,chckSKEG,chckLOG,chckSOG,chckLKDG,chckLOBDG,chckSKDG,chckSOBDG};

        for (int i = 0; i < 10; i++) {

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
        parkettControl.loescheSonderwuensche();
        for(int i : list)
        parkettControl.speichereSonderwunsch(i);
    }

    public void oeffneParkettView() {
        super.oeffneBasisView();
    }

    protected void getParkettKunde() throws SQLException, ClassNotFoundException {
        List<Integer> liste= parkettControl.connectKunde();
        for(int i:liste){
            if(i==1)
                chckLEEG.setSelected(true);
            if(i==2)
                chckLKEG.setSelected(true);
            if(i==3)
                chckSEEG.setSelected(true);
            if(i==4)
                chckSKEG.setSelected(true);
            if(i==5)
                chckLOG.setSelected(true);
            if(i==6)
                chckSOG.setSelected(true);
            if(i==7)
                chckLKDG.setSelected(true);
            if(i==8)
                chckLOBDG.setSelected(true);
            if(i==9)
                chckSKDG.setSelected(true);
            if(i==10)
                chckSOBDG.setSelected(true);


        }

    }

    protected void setInhalt() {
        if (parkettModel != null) {
            List<Parkett> la = parkettModel.laodParkett();
            txtLEEG.setText(la.get(0).getPreis() + "");
            txtLKEG.setText(la.get(1).getPreis() + "");
            txtSEEG.setText(la.get(2).getPreis() + "");
            txtSKEG.setText(la.get(3).getPreis() + "");
            txtLOG.setText(la.get(4).getPreis() + "");
            txtSOG.setText(la.get(5).getPreis() + "");
            txtLKDG.setText(la.get(6).getPreis() + "");
            txtLOBDG.setText(la.get(7).getPreis() + "");
            txtSKDG.setText(la.get(8).getPreis() + "");
            txtSOBDG.setText(la.get(9).getPreis() + "");

        }
    }

    public List<Integer> getChcks() {
        List <Integer> list = new ArrayList<>();

        if(chckLEEG.isSelected())
            list.add(1);
             if(chckLKEG.isSelected())
                list.add(2);
                if(chckSEEG.isSelected())
                    list.add(3);
                     if(chckSKEG.isSelected())
                         list.add(4);
                         if(chckLOG.isSelected())
                             list.add(5);
                             if(chckSOG.isSelected())
                                list.add(6);
                                if(chckLKDG.isSelected())
                                    list.add(7);
                                if(chckLOBDG.isSelected())
                                         list.add(8);
                                    if(chckSKDG.isSelected())
                                            list.add(9);
                                        if(chckSKDG.isSelected())
                                                list.add(10);


        return list;
    }


}




