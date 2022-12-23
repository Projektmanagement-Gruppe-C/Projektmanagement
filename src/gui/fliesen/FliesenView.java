package gui.fliesen;

import business.fliesen.Fliesen;
import business.fliesen.FliesenModel;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FliesenView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private FliesenControl fliesenControl;
    private FliesenModel fliesenModel;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lblKFEG = new Label("Keine Fliesen im Küchenbereich des EG");
    private Label lblKFOG= new Label("Keine Fliesen im Bad des OG");
    private Label lblMFEG = new Label("Mehrpreis bei großformatige Fliesen im Küchenbereich des EG");
    private Label lblMFOG = new Label("Mehrpreis bei großformatige Fliesen im Bad des OG");
    private Label lblFBDG = new Label("Fliesen im Bad des DG");
    private Label lblMFDG = new Label("Mehrpreis bei großformatige Fliesen im Bad des DG");

    private TextField txtKFEG= new TextField();
    private TextField txtKFOG = new TextField();
    private TextField txtMFEG = new TextField();
    private TextField txtMFOG = new TextField();
    private TextField txtFBDG = new TextField();
    private TextField txtMFDG = new TextField();

    private CheckBox chckKFEG = new CheckBox();
    private CheckBox chckKFOG = new CheckBox();
    private CheckBox chckMFEG = new CheckBox();
    private CheckBox chckMFOG = new CheckBox();
    private CheckBox chckFBDG = new CheckBox();
    private CheckBox chckMFDG = new CheckBox();


    // -------Ende Attribute der grafischen Oberflaeche-------

    public FliesenView(FliesenControl fliesenControl, Stage fliesenStage, FliesenModel fliesenModel) throws SQLException, ClassNotFoundException {
        super(fliesenStage);
        this.fliesenControl = fliesenControl;
        fliesenStage.setTitle("Sonderwuensche zu Fliesen-Varianten");

       // this.fliesenControl.leseFliesenSonderwuensche();
        this.fliesenModel = fliesenModel;
        this.initKomponenten();
        setInhalt();
        getFliesenKunde();
    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = { lblKFEG,lblKFOG,lblMFEG,lblMFOG,lblFBDG,lblMFDG};

        TextField[] txtFieldArray = { txtKFEG,txtKFOG,txtMFEG,txtMFOG,txtFBDG,txtMFDG};

        CheckBox[] checkBoxArray = {chckKFEG, chckKFOG,chckMFEG,chckMFOG,chckFBDG,chckMFDG};

        for (int i = 0; i < 6; i++) {

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
        fliesenControl.loescheSonderwuensche();
        for(int i : list)
        fliesenControl.speichereSonderwunsch(i);
    }

    public void oeffneFliesenView() {
        super.oeffneBasisView();
    }

    protected void getFliesenKunde() throws SQLException, ClassNotFoundException {
        List<Integer> liste=fliesenControl.connectKunde();
        for(int i:liste){
            if(i==1)
                chckKFEG.setSelected(true);
            else if(i==2)
                chckKFOG.setSelected(true);
            else if(i==3)
                chckMFEG.setSelected(true);
            else if(i==4)
                chckMFOG.setSelected(true);
            else if(i==5)
                chckFBDG.setSelected(true);
            else if(i==6)
                chckMFDG.setSelected(true);

        }

    }

    protected void setInhalt() {
        if (fliesenModel != null) {
            List<Fliesen> la = fliesenModel.laodFliesen();
            txtKFEG.setText(la.get(0).getPreis() + "");
            txtKFOG.setText(la.get(1).getPreis() + "");
            txtMFEG.setText(la.get(2).getPreis() + "");
            txtMFOG.setText(la.get(3).getPreis() + "");
            txtFBDG.setText(la.get(4).getPreis() + "");
            txtMFDG.setText(la.get(5).getPreis() + "");
        }
    }

    public List<Integer> getChcks() {
        List <Integer> list = new ArrayList<>();

        if(chckKFEG.isSelected())
            list.add(1);
             if(chckKFOG.isSelected())
                list.add(2);
                if(chckMFEG.isSelected())
                    list.add(3);
                     if(chckMFOG.isSelected())
                         list.add(4);
                         if(chckFBDG.isSelected())
                             list.add(5);
                             if(chckMFDG.isSelected())
                                list.add(6);

        return list;
    }


}




