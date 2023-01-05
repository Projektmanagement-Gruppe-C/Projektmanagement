package gui.sanitaer;

import business.fenster_aussentuer.FensterAussentuerModel;
import business.fenster_aussentuer.FenterAussentuer;
import business.sanitaer.Sanitaer;
import business.sanitaer.SanitaerModel;
import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanitaerView extends BasisView {

    // das Control-Objekt des Grundriss-Fensters
    private SanitaerControl control;
    private SanitaerModel sanitaerModel;

    // ---Anfang Attribute der grafischen Oberflaeche---
    private Label lbl1 = new Label("Mehrpreis für ein größeres Waschbecken im OG");
    private Label lbl2 = new Label("Mehrpreis für ein größeres Waschbecken im DG");
    private Label lbl3 = new Label("Mehrpreis für eine bodentiefe Dusche im OG");
    private Label lbl4 = new Label("Mehrpreis für eine bodentiefe Dusche im DG");


    private TextField txt1 = new TextField();
    private TextField txt2 = new TextField();
    private TextField txt3 = new TextField();
    private TextField txt4 = new TextField();


    private CheckBox chck1 = new CheckBox();
    private CheckBox chck2 = new CheckBox();
    private CheckBox chck3 = new CheckBox();
    private CheckBox chck4 = new CheckBox();

    public void checkboxDeaktivieren(List<Integer> list, CheckBox ch, int index, boolean disable) {
        ch.setSelected(false);
        list.remove(new Integer(index));
        if (disable) {
            ch.setDisable(true);
        }
    }

    public List<Integer> validierung(List<Integer> listSanitaer) {
        if (listSanitaer.contains(1)) {
            checkboxDeaktivieren(listSanitaer,chck3,3,false);
        }

        if (control.hatDachgeschoss() && listSanitaer.contains(2)) {
            checkboxDeaktivieren(listSanitaer,chck4,4,false);
        }

        if (!control.hatDachgeschoss()) {
            checkboxDeaktivieren(listSanitaer,chck2,2,false);
            checkboxDeaktivieren(listSanitaer,chck4,4,false);
        }
        return listSanitaer;
    }

    // -------Ende Attribute der grafischen Oberflaeche-------

    public SanitaerView(SanitaerControl sanitaerControl, Stage stage, SanitaerModel model) throws SQLException, ClassNotFoundException {
        super(stage);
        this.control = sanitaerControl;
        stage.setTitle("Sonderwuensche zu Sanitaer-Varianten");

       // this.aussenanlageControl.leseAussenanlageSonderwuensche();
        this.sanitaerModel = model;
        this.initKomponenten();
        setInhalt();
        getSanitaerKunde();

    }

    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        super.initKomponenten();

        Label[] lblSonderwunschArray = { lbl1, lbl2,lbl3,lbl4};

        TextField[] txtFieldArray = {txt1,txt2,txt3,txt4};

        CheckBox[] checkBoxArray = {chck1,chck2,chck3,chck4};

        for (int i = 0; i < 4; i++) {

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

        list = validierung(list);


        control.loescheSonderwuensche();
        for(int i : list)
            control.speichereSonderwunsch(i);
    }

    public void oeffneSanitaerView() {
        super.oeffneBasisView();
    }

    protected void getSanitaerKunde() throws SQLException, ClassNotFoundException {
        List<Integer> liste= control.connectKunde();
        for(int i:liste){
            if(i==1)
                chck1.setSelected(true);
            else if(i==2)
                chck2.setSelected(true);
            else if(i==3)
                chck2.setSelected(true);
            else if(i==4)
                chck2.setSelected(true);

        }

    }

    protected void setInhalt() {
        if (sanitaerModel != null) {
            List<Sanitaer> la = sanitaerModel.laodSanitaer();
            txt1.setText(la.get(0).getPreis() + "");
            txt2.setText(la.get(1).getPreis() + "");
            txt3.setText(la.get(2).getPreis() + "");
            txt4.setText(la.get(3).getPreis() + "");
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

        return list;
    }
}




