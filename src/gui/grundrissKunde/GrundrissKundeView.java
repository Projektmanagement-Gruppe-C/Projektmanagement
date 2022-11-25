package gui.grundrissKunde;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class GrundrissKundeView {

    Stage grundrissKundeStage;

    //---Anfang Attribute der grafischen Oberflaeche---

    private final BorderPane borderPane 	= new BorderPane();
    private final GridPane gridPane 		= new GridPane();
    private final Label lblSonderwunsch   	= new Label("Grundriss Kundenwunsch");

    private final Label lblWandKueche = new Label("Wand zur Abtrennung der Küche von dem Essbereich");
    private final TextField txtPreisWandKueche 	= new TextField();

    private final Label lblTurKueche = new Label("Tür in der Wand zwischen Küche und Essbereich");
    private final TextField txtTurKueche 	= new TextField();

    private final Label lblGrossZimmer = new Label("Großes Zimmer im OG statt zwei kleine Zimmern");
    private final TextField txtGrossZimmer 	= new TextField();

    private final Label lblAbtTreppen = new Label("Abgetrennter Treppenraum im DG");
    private final TextField txtAbtTreppen 	= new TextField();

    private final Label lblVorBad = new Label("Vorrichtung eines Bades im DG");
    private final TextField txtVorBad 	= new TextField();

    private final Label lblAusBad = new Label("Ausführung eines Bades im DG");
    private final TextField txtAusBad 	= new TextField();

    private final Label lblGesamtP = new Label("Gesamtpreis");
    private final TextField txtGesamtP 	= new TextField();

    //-------Ende Attribute der grafischen Oberflaeche-------



    /**
     * erzeugt ein GrundrissView-Objekt, belegt das zugehoerige Control
     * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
     */
    public GrundrissKundeView(Stage grundrissKundeStage){
        this.grundrissKundeStage = grundrissKundeStage;
        Scene scene = new Scene(borderPane, 450, 350);
        grundrissKundeStage.setScene(scene);
        grundrissKundeStage.setResizable(false);
        grundrissKundeStage.setWidth(600);
        grundrissKundeStage.setTitle("Kundenwunsch Grundrissvarianten");
        this.initKomponenten();
    }


    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten() {
        borderPane.setCenter(gridPane);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(lblSonderwunsch, 0, 0);
        lblSonderwunsch.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        gridPane.add(lblWandKueche, 0, 1);
        gridPane.add(txtPreisWandKueche, 1 , 1);
        txtPreisWandKueche.setEditable(false);

        gridPane.add(lblTurKueche, 0, 2);
        gridPane.add(txtTurKueche, 1 , 2);
        txtPreisWandKueche.setEditable(false);

        gridPane.add(lblGrossZimmer, 0, 3);
        gridPane.add(txtGrossZimmer, 1 , 3);
        txtGrossZimmer.setEditable(false);

        gridPane.add(lblAbtTreppen, 0, 4);
        gridPane.add(txtAbtTreppen, 1 , 4);
        txtAbtTreppen.setEditable(false);

        gridPane.add(lblVorBad, 0, 5);
        gridPane.add(txtVorBad, 1 , 5);
        txtVorBad.setEditable(false);

        gridPane.add(lblAusBad, 0, 6);
        gridPane.add(txtAusBad, 1 , 6);
        txtAusBad.setEditable(false);

        gridPane.add(lblGesamtP, 0, 7);
        gridPane.add(txtGesamtP, 1 , 7);
        txtPreisWandKueche.setEditable(false);
    }



    /**
     * macht das GrundrissView-Objekt sichtbar.
     */
    protected void oeffneBasisView(){
        grundrissKundeStage.showAndWait();
    }


}
