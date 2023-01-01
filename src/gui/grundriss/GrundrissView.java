package gui.grundriss;

import business.fenster_aussentuer.FensterAussentuerModel;
import business.fenster_aussentuer.FenterAussentuer;
import business.grundriss.Grundriss;
import business.grundriss.GrundrissModel;
import gui.basis.BasisView;
import gui.fenster_aussentuer.FensterAussentuerControl;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu 
 * den Grundrissvarianten bereitstellt.
 */
public class GrundrissView extends BasisView{
 
 	// das Control-Objekt des Grundriss-Fensters
	private GrundrissControl control;
	private GrundrissModel model;

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Label lbl1 = new Label("Wand zur Abtrennung der Küche von dem Essbereich");
	private Label lbl2 = new Label("Tür in der Wand zwischen Küche und Essbereich");
	private Label lbl3 = new Label("Großes Zimmer im OG statt zwei kleinen Zimmern");
	private Label lbl4 = new Label("Abgetrennter Treppenraum im DG");
	private Label lbl5 = new Label("Vorrichtung eines Bades im DG");
	private Label lbl6 = new Label("Ausführung eines Bades im DG");

	private TextField txt1 = new TextField();
	private TextField txt2 = new TextField();
	private TextField txt3 = new TextField();
	private TextField txt4 = new TextField();
	private TextField txt5 = new TextField();
	private TextField txt6 = new TextField();

	private CheckBox chck1 = new CheckBox();
	private CheckBox chck2 = new CheckBox();
	private CheckBox chck3 = new CheckBox();
	private CheckBox chck4 = new CheckBox();
	private CheckBox chck5 = new CheckBox();
	private CheckBox chck6 = new CheckBox();

	// -------Ende Attribute der grafischen Oberflaeche-------

	public GrundrissView(GrundrissControl grundrissControl, Stage stage, GrundrissModel grundrissModel) throws SQLException, ClassNotFoundException {
		super(stage);
		this.control = grundrissControl;
		stage.setTitle("Sonderwuensche zu Grundriss-Varianten");

		// this.aussenanlageControl.leseAussenanlageSonderwuensche();
		this.model = grundrissModel;
		this.initKomponenten();
		setInhalt();
		getGrundrissKunde();


	}

	/* initialisiert die Steuerelemente auf der Maske */
	protected void initKomponenten() {
		super.initKomponenten();

		Label[] lblSonderwunschArray = { lbl1, lbl2, lbl3 ,lbl4,lbl5,lbl6};

		TextField[] txtFieldArray = {txt1, txt2, txt3, txt4, txt5,txt6};

		CheckBox[] checkBoxArray = {chck1,chck2,chck3,chck4,chck5,chck6};

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

	public void checkboxDeaktivieren(List<Integer> list, CheckBox ch, int index,boolean disable) {
		ch.setSelected(false);
		list.remove(new Integer(index));
		if (disable) {
			ch.setDisable(true);
		}
	}

	public List<Integer> validierung(List<Integer> list) {

		if(!(control.hatDachgeschoss())) {
			checkboxDeaktivieren(list,chck4,4,false);

			checkboxDeaktivieren(list,chck5,5,false);

			checkboxDeaktivieren(list,chck6,6,false);
		}

		if(!list.contains(1)) {
			checkboxDeaktivieren(list,chck2,2,false);
		}

		if(!(control.hatDachgeschoss() && list.contains(5))) {
			checkboxDeaktivieren(list,chck6,6,false);
		}
		return list;
	}

	@Override
	protected void speichereSonderwuensche() throws SQLException, ClassNotFoundException {
		List<Integer> list = getChcks();

		list = validierung(list);

		control.loescheSonderwuensche();
		for(int i : list) {
			control.speichereSonderwunsch(i);
		}

	}

	public void oeffneGrundrissView() {
		super.oeffneBasisView();
	}

	protected void getGrundrissKunde() throws SQLException, ClassNotFoundException {
		List<Integer> liste= control.connectKunde();
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
		}

	}

	protected void setInhalt() {
		if (model != null) {
			List<Grundriss> la = model.loadGrundriss();
			txt1.setText(la.get(0).getPreis() + "");
			txt2.setText(la.get(1).getPreis() + "");
			txt3.setText(la.get(2).getPreis() + "");
			txt4.setText(la.get(3).getPreis() + "");
			txt5.setText(la.get(4).getPreis() + "");
			txt6.setText(la.get(5).getPreis() + "");
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

		return list;
	}
}



