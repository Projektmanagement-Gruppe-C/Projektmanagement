package gui.fensterAussentuere;

import java.util.ArrayList;
import java.util.List;

import gui.basis.BasisView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FensterTuereView extends BasisView
{
	private FensterTuereControl control;

    private Label lblFenster = new Label("Sonderwunsch 1");
    private TextField txtPreisFenster = new TextField();
    private Label lblEuroFenster = new Label("Euro");
    private CheckBox cbEuroFenster = new CheckBox();
    
    private Label lblFenster2 = new Label("Sonderwunsch 2");
    private TextField txtPreisFenster2 = new TextField();
    private Label lblEuroFenster2 = new Label("Euro");
    private CheckBox cbEuroFenster2 = new CheckBox();
    
    private Label lblFenster3 = new Label("Sonderwunsch 3");
    private TextField txtPreisFenster3 = new TextField();
    private Label lblEuroFenster3 = new Label("Euro");
    private CheckBox cbEuroFenster3 = new CheckBox();
    
    private Label lblFenster4 = new Label("Sonderwunsch 4");
    private TextField txtPreisFenster4 = new TextField();
    private Label lblEuroFenster4 = new Label("Euro");
    private CheckBox cbEuroFenster4 = new CheckBox();
    
    private Label lblFenster5 = new Label("Sonderwunsch 5");
    private TextField txtPreisFenster5 = new TextField();
    private Label lblEuroFenster5 = new Label("Euro");
    private CheckBox cbEuroFenster5 = new CheckBox();
    
    private Label lblFenster6 = new Label("Sonderwunsch 6");
    private TextField txtPreisFenster6 = new TextField();
    private Label lblEuroFenster6 = new Label("Euro");
    private CheckBox cbEuroFenster6 = new CheckBox();
    
    private Label lblFenster7 = new Label("Sonderwunsch 7");
    private TextField txtPreisFenster7 = new TextField();
    private Label lblEuroFenster7 = new Label("Euro");
    private CheckBox cbEuroFenster7 = new CheckBox();
    
    private Label lblFenster8 = new Label("Sonderwunsch 8");
    private TextField txtPreisFenster8 = new TextField();
    private Label lblEuroFenster8 = new Label("Euro");
    private CheckBox cbEuroFenster8 = new CheckBox();
    
    private Label lblFenster9 = new Label("Sonderwunsch 9");
    private TextField txtPreisFenster9 = new TextField();
    private Label lblEuroFenster9 = new Label("Euro");
    private CheckBox cbEuroFenster9 = new CheckBox();
    
    private TextField gesamtPreisFenster10 = new TextField();
    
    public FensterTuereView (FensterTuereControl control, Stage stage){
    	super(stage);
        this.control = control;
        stage.setTitle("Sonderwünsche - Fenster und Außentüren");
                
	    this.initKomponenten();
    }
  
    protected void initKomponenten(){
    	super.initKomponenten();   	
       	super.getLblSonderwunsch().setText("Fenster und Aussentüren");
       	super.getGridPaneSonderwunsch().add(lblFenster, 0, 1);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster, 1, 1);
    	this.txtPreisFenster.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster, 2, 1);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster, 3, 1);
    	
       	super.getGridPaneSonderwunsch().add(lblFenster2, 0, 2);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster2, 1, 2);
    	this.txtPreisFenster2.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster2, 2, 2);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster2, 3, 2);
    	
       	super.getGridPaneSonderwunsch().add(lblFenster3, 0, 3);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster3, 1, 3);
    	this.txtPreisFenster3.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster3, 2, 3);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster3, 3, 3);
    	
       	super.getGridPaneSonderwunsch().add(lblFenster4, 0, 4);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster4, 1, 4);
    	this.txtPreisFenster4.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster4, 2, 4);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster4, 3, 4);
    	
    	super.getGridPaneSonderwunsch().add(lblFenster5, 0, 5);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster5, 1, 5);
    	this.txtPreisFenster5.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster5, 2, 5);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster5, 3, 5);
    	
    	super.getGridPaneSonderwunsch().add(lblFenster6, 0, 6);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster6, 1, 6);
    	this.txtPreisFenster6.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster6, 2, 6);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster6, 3, 6);
    	
    	super.getGridPaneSonderwunsch().add(lblFenster7, 0, 7);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster7, 1, 7);
    	this.txtPreisFenster7.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster7, 2, 7);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster7, 3, 7);
    	
    	super.getGridPaneSonderwunsch().add(lblFenster8, 0, 8);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster8, 1, 8);
    	this.txtPreisFenster8.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster8, 2, 8);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster8, 3, 8);
    	
    	super.getGridPaneSonderwunsch().add(lblFenster9, 0, 9);
    	super.getGridPaneSonderwunsch().add(txtPreisFenster9, 1, 9);
    	this.txtPreisFenster9.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblEuroFenster9, 2, 9);
    	super.getGridPaneSonderwunsch().add(cbEuroFenster9, 3, 9);
    	
    	super.getGridPaneSonderwunsch().add(gesamtPreisFenster10, 2, 10);
    	this.gesamtPreisFenster10.setText("Gesamtpreis in €");
    	this.gesamtPreisFenster10.setEditable(false);
    }  
    
	public void oeffneFensterTuereView(){ 
		super.oeffneBasisView();
	}

		protected void berechneUndZeigePreisSonderwuensche() {
		// TODO Auto-generated method stub
		int preis = 0;
		int[] ausgewaehlteWuensche = new int[9];
		if(cbEuroFenster.isSelected())
		{
			ausgewaehlteWuensche[0] = 1;
			preis += Integer.parseInt(txtPreisFenster.getText());
		}
		if(cbEuroFenster2.isSelected())
		{
			ausgewaehlteWuensche[1] = 1;
			preis += Integer.parseInt(txtPreisFenster2.getText());
		}
		if(cbEuroFenster3.isSelected())
		{
			ausgewaehlteWuensche[2] = 1;
			preis += Integer.parseInt(txtPreisFenster3.getText());
		}
		if(cbEuroFenster4.isSelected())
		{
			ausgewaehlteWuensche[3] = 1;
			preis += Integer.parseInt(txtPreisFenster4.getText());
		}
		if(cbEuroFenster5.isSelected())
		{
			ausgewaehlteWuensche[4] = 1;
			preis += Integer.parseInt(txtPreisFenster5.getText());
		}
		if(cbEuroFenster6.isSelected())
		{
			ausgewaehlteWuensche[5] = 1;
			preis += Integer.parseInt(txtPreisFenster6.getText());
		}
		if(cbEuroFenster7.isSelected())
		{
			ausgewaehlteWuensche[6] = 1;
			preis += Integer.parseInt(txtPreisFenster7.getText());
		}
		if(cbEuroFenster8.isSelected())
		{
			ausgewaehlteWuensche[7] = 1;
			preis += Integer.parseInt(txtPreisFenster8.getText());
		}
		if(cbEuroFenster9.isSelected())
		{
			ausgewaehlteWuensche[8] = 1;
			preis += Integer.parseInt(txtPreisFenster9.getText());
		}
		if (pruefeKonstellationSonderwuensche()) {
			System.out.println("Preis: " + preis);
		} else {
			System.out.println("Fehler, diese Kombination ist nicht Möglich");
		}
	}
	private boolean pruefeKonstellationSonderwuensche() {
		return control.pruefeKonstellationSonderwuensche(getAusgewaehlteWuensche());
	}
	private List<Integer> getAusgewaehlteWuensche() {
		List<Integer> ausgewaehlteWuensche = new ArrayList<>();

		if (cbEuroFenster.isSelected())
			ausgewaehlteWuensche.add(1);
		if (cbEuroFenster2.isSelected())
			ausgewaehlteWuensche.add(2);
		if (cbEuroFenster3.isSelected())
			ausgewaehlteWuensche.add(3);
		if (cbEuroFenster4.isSelected())
			ausgewaehlteWuensche.add(4);
		if (cbEuroFenster5.isSelected())
			ausgewaehlteWuensche.add(5);
		if (cbEuroFenster6.isSelected())
			ausgewaehlteWuensche.add(6);
		if (cbEuroFenster7.isSelected())
			ausgewaehlteWuensche.add(7);
		if (cbEuroFenster8.isSelected())
			ausgewaehlteWuensche.add(8);
		if (cbEuroFenster9.isSelected())
			ausgewaehlteWuensche.add(9);
		return ausgewaehlteWuensche;
	}
	
	public void setSonderwunsch(int sonderwuensche) {
		switch (sonderwuensche) {
		case 1:
			cbEuroFenster.setSelected(true);
			break;
		case 2:
			cbEuroFenster2.setSelected(true);
			break;
		case 3:
			cbEuroFenster3.setSelected(true);
			break;
		case 4:
			cbEuroFenster4.setSelected(true);
			break;
		case 5:
			cbEuroFenster5.setSelected(true);
			break;
		case 6:
			cbEuroFenster6.setSelected(true);
			break;
		case 7:
			cbEuroFenster7.setSelected(true);
			break;
		case 8:
			cbEuroFenster8.setSelected(true);
			break;
		case 9:
			cbEuroFenster9.setSelected(true);
			break;
		default:
			System.out.println("Fehler beim Wunsch setzen!");
		}
	}
	@Override
	protected void speichereSonderwuensche() {
		control.speichereKonstellation(getAusgewaehlteWuensche());
	}

	@Override
	protected void schreibeInCSV() {
		// TODO Auto-generated method stub
		
	}
 	
}
