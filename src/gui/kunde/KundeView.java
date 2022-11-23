package gui.kunde;

import business.kunde.*;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * Klasse, welche das Grundfenster mit den Kundendaten bereitstellt.
 */
public class KundeView {

    // das Control-Objekt des Grundfensters mit den Kundendaten
    private final KundeControl kundeControl;
    // das Model-Objekt des Grundfensters mit den Kundendaten
    private final KundeModel kundeModel;

    //---Anfang Attribute der grafischen Oberflaeche---
    private final BorderPane borderPane = new BorderPane();
    private final GridPane gridPane = new GridPane();
    private final Label lblKunde = new Label("Kunde");

    private final Label lblNummerHaus = new Label("Hausnummer");
    private final ComboBox<Integer> cmbBxNummerHaus = new ComboBox<>();
    private final Label lblPlannummer = new Label("Plannummer des Hauses");
    private final TextField txtPlannummer = new TextField();

    private final Label lblVorname = new Label("Vorname");
    private final TextField txtVorname = new TextField();
    private final Label lblKundennummer = new Label("Kundennummer");
    private final TextField txtKundennummer = new TextField();
    private final Label lblNachname = new Label("Nachname");
    private final TextField txtNachname = new TextField();
    private final Label lblTelefonnummer = new Label("Telefonnummer");
    private final TextField txtTelefonnummer = new TextField();
    private final Label lblEmail = new Label("E-Mail Adresse");
    private final TextField txtEmail = new TextField();
    private final Button btnAnlegen = new Button("Anlegen");
    private final Button btnAendern = new Button("Ändern");
    private final Button btnLoeschen = new Button("Löschen");
    private final Button btnZeigeGrundrissKunde = new Button("Grundrisse");
    private final MenuBar mnBar = new MenuBar();
    private final Menu mnSonderwuensche = new Menu("Sonderwünsche");
    private final MenuItem mnItmGrundriss = new MenuItem("Grundrissvarianten");
    private final MenuItem mnItmAussenanlage  = new MenuItem("Aussenanlage");
    private final MenuItem mnItmInnentueren = new MenuItem("Innentueren");
    


    //-------Ende Attribute der grafischen Oberflaeche-------

    /**
     * erzeugt ein KundeView-Objekt und initialisiert die Steuerelemente der Maske
     *
     * @param kundeControl KundeControl, enthaelt das zugehoerige Control
     * @param primaryStage Stage, enthaelt das Stage-Objekt fuer diese View
     * @param kundeModel   KundeModel, enthaelt das zugehoerige Model
     */
    public KundeView(KundeControl kundeControl, Stage primaryStage,
                     KundeModel kundeModel) {
        this.kundeControl = kundeControl;
        this.kundeModel = kundeModel;

        primaryStage.setTitle(this.kundeModel.getUeberschrift());
        Scene scene = new Scene(borderPane, 550, 550);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        this.initKomponenten();
        this.initListener();
    }


    /* initialisiert die Steuerelemente auf der Maske */
    private void initKomponenten() {
        borderPane.setCenter(gridPane);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(lblKunde, 0, 1);
        lblKunde.setMinSize(150, 40);
        lblKunde.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(lblNummerHaus, 0, 2);
        gridPane.add(cmbBxNummerHaus, 1, 2);
        cmbBxNummerHaus.setMinSize(150, 25);
        cmbBxNummerHaus.setItems(this.kundeModel.getPlannummern());

        gridPane.add(lblPlannummer, 0, 3);
        gridPane.add(txtPlannummer, 1, 3);
        gridPane.add(lblKundennummer, 0, 4);
        gridPane.add(txtKundennummer, 1, 4);
        //txtKundennummer.setPromptText("Kundennummer eingeben");
        gridPane.add(lblVorname, 0, 5);
        gridPane.add(txtVorname, 1, 5);
        gridPane.add(lblNachname, 0, 6);
        gridPane.add(txtNachname, 1, 6);
        gridPane.add(lblTelefonnummer, 0, 7);
        gridPane.add(txtTelefonnummer, 1, 7);
        gridPane.add(lblEmail, 0, 8);
        gridPane.add(txtEmail, 1, 8);
        // Buttons
        gridPane.add(btnAnlegen, 0, 12);
        btnAnlegen.setMinSize(150, 25);
        gridPane.add(btnAendern, 1, 12);
        btnAendern.setMinSize(150, 25);
        gridPane.add(btnLoeschen, 2, 12);
        btnLoeschen.setMinSize(150, 25);
        gridPane.add(btnZeigeGrundrissKunde, 0, 13);
        btnZeigeGrundrissKunde.setMinSize(150, 25);

        btnLoeschen.setMinSize(150, 25);
        // MenuBar und Menu
        borderPane.setTop(mnBar);
        mnBar.getMenus().add(mnSonderwuensche);
        mnSonderwuensche.getItems().add(mnItmGrundriss);
        mnSonderwuensche.getItems().add(mnItmAussenanlage);
        mnSonderwuensche.getItems().add(mnItmInnentueren);
    }

    /* initialisiert die Listener zu den Steuerelementen auf der Maske */
    private void initListener() {
        cmbBxNummerHaus.setOnAction(event -> {
            int plannummer = cmbBxNummerHaus.getValue();
            holeInfoDachgeschoss();
            kundeControl.loadKundeByPlannummer(plannummer);
        });
        btnAnlegen.setOnAction(aEvent -> legeKundenAn());
        btnAendern.setOnAction(aEvent -> aendereKunden());
        btnLoeschen.setOnAction(aEvent -> loescheKunden());
        mnItmGrundriss.setOnAction(aEvent -> kundeControl.oeffneGrundrissControl());
        mnItmAussenanlage.setOnAction(actionEvent -> kundeControl.oeffneAussenanlageControl());
        mnItmInnentueren.setOnAction(aEvent -> kundeControl.oeffneInnentuerenControl());
        btnZeigeGrundrissKunde.setOnAction(aEvent -> kundeControl.oeffneGrundrissKundeControl());
        //btnZeigeGrundrissKunde.setOnAction(aEvent -> kundeControl.oeffneHundeControl());


    }

    private void holeInfoDachgeschoss() {
    }

    private void leseKunden() {
    }

    private void legeKundenAn() {
        Kunde kunde = null;
        // Objekt kunde fuellen
        kundeControl.speichereKunden(kunde);
    }

    private void aendereKunden() {
    }

    private void loescheKunden() {
    }

    public void setKundeDaten(Kunde kunde) {
        if (kunde != null) {
            txtVorname.setText(kunde.getVorname());
        } else {
            txtVorname.setText("");
        }
    }

    /**
     * zeigt ein Fehlermeldungsfenster an
     *
     * @param ueberschrift, Ueberschrift fuer das Fehlermeldungsfenster
     * @param meldung,      String, welcher die Fehlermeldung enthaelt
     */
    public void zeigeFehlermeldung(String ueberschrift, String meldung) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fehlermeldung");
        alert.setHeaderText(ueberschrift);
        alert.setContentText(meldung);
        alert.show();
    }

}


