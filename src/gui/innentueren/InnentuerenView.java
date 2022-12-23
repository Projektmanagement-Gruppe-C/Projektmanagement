package gui.innentueren;

import business.aussenanlage.Aussenanlage;
import business.aussenanlage.AussenanlageModel;
import business.innentueren.Innentueren;
import business.innentueren.InnentuerenModel;
import gui.aussenanlage.AussenanlageControl;
import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu 
 * den Grundrissvarianten bereitstellt.
 */
public class InnentuerenView extends BasisView {

	// das Control-Objekt des Grundriss-Fensters
	private InnentuerenControl control;
	private InnentuerenModel model;


	//---Anfang Attribute der grafischen Oberflaeche---
	private final Label lblKlarglas = new Label("Mehrpreis f�r die Ausf�hrung eines Glasausschnitts" + "(Klarglas) in einer Innentuer: ");
	private final TextField txtPreisKlarglas 	= new TextField();
	private final Label lblKlarglasEuro 		= new Label("Euro pro Tuer");
	private final TextField anzahlKlarglas      = new TextField("0");

	private final Label lblMilchglas = new Label("Mehrpreis f�r die Ausf�hrung eines Glasausschnitts" + "(Milchglas) in einer Innentuer: ");
	private final TextField txtPreisMilchglas 	= new TextField();
	private final Label lblMilchglasEuro 		= new Label("Euro pro Tuer");
	private final TextField anzahlMilchglas     = new TextField("0");

	private final Label lblGarageHolz = new Label("Innent�r zur Garage als Holzt�r:");
	private final TextField txtPreisGarageHolz 	= new TextField();
	private final CheckBox chckBxGarageHolz		= new CheckBox();


	private final Label lblGesamtP = new Label("Gesamtpreis in Euro: ");
	private final TextField txtGesamtP 	= new TextField();


	//-------Ende Attribute der grafischen Oberflaeche-------

	public InnentuerenView(InnentuerenControl innentuerenControl, Stage innentuerenStage,InnentuerenModel innentuerenModel) throws SQLException, ClassNotFoundException {
		super(innentuerenStage);
		this.control = innentuerenControl;
		innentuerenStage.setTitle("Sonderwuensche zu Innentueren-Varianten");

		// this.aussenanlageControl.leseAussenanlageSonderwuensche();
		this.model = innentuerenModel;
		this.initKomponenten();
		setInhalt();
		getInnentuerenKunde();


	}

	/* initialisiert die Steuerelemente auf der Maske */
	protected void initKomponenten() {
		super.initKomponenten();

		Label[] lblSonderwunschArray = {lblKlarglas,lblKlarglasEuro ,lblMilchglas,
				lblMilchglasEuro,lblGarageHolz,lblGesamtP };

		TextField[] txtFieldArray = { txtPreisKlarglas,anzahlKlarglas,txtPreisMilchglas
				,anzahlMilchglas,txtPreisGarageHolz,txtGesamtP};

		CheckBox[] checkBoxArray = {chckBxGarageHolz};

		for (int i = 0; i < 6; i++)
			super.getGridPaneSonderwunsch().add(lblSonderwunschArray[i], 0, (i + 1));

			super.getGridPaneSonderwunsch().add(txtFieldArray[0], 1, (1));
			txtFieldArray[0].setEditable(false);
			super.getGridPaneSonderwunsch().add(txtFieldArray[1], 1, (2));
			super.getGridPaneSonderwunsch().add(txtFieldArray[2], 1, (3));
			txtFieldArray[2].setEditable(false);
			super.getGridPaneSonderwunsch().add(txtFieldArray[3], 1, (4));
			super.getGridPaneSonderwunsch().add(txtFieldArray[4], 1, (5));
			txtFieldArray[4].setEditable(false);
			super.getGridPaneSonderwunsch().add(txtFieldArray[5], 1, (6));
			txtFieldArray[5].setEditable(false);
			super.getGridPaneSonderwunsch().add(checkBoxArray[0], 3, (1));


	}

	@Override
	protected void berechneUndZeigePreisSonderwuensche() {
		int gsmtPreis;
	}

	@Override
	protected void speichereSonderwuensche() throws SQLException, ClassNotFoundException {
		List<Integer> list = getChcks();
		control.loescheSonderwuensche();
		for(int i : list)
			control.speichereSonderwunsch(i);
	}

	public void oeffneInnentuerenView() {
		super.oeffneBasisView();
	}

	protected void getInnentuerenKunde() throws SQLException, ClassNotFoundException {
		List<Integer> liste=control.connectKunde();
		for(int i:liste){
			if(i==1)
				chckBxGarageHolz.setSelected(true);

		}

	}

	protected void setInhalt() {
		if (model != null) {
			List<Innentueren> la = model.loadInnentueren();
			txtPreisKlarglas.setText(la.get(0).getPreis() + "");
			txtPreisMilchglas.setText(la.get(1).getPreis() + "");
			txtPreisGarageHolz.setText(la.get(2).getPreis() + "");
		}
	}
//TODO
	public List<Integer> getChcks() {
		List <Integer> list = new ArrayList<>();

		if(chckBxGarageHolz.isSelected())
			list.add(1);

		return list;
	}
}
