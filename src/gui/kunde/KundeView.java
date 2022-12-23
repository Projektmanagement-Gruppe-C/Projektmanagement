package gui.kunde;

import business.aussenanlage.AussenanlageModel;
import business.kunde.*;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    private final Label lblNummerHaus = new Label("Plannummer des Hauses");
    private final ComboBox<Integer> cmbBxNummerHaus = new ComboBox<>();
    private final Label lblVorname = new Label("Vorname");
    private final TextField txtVorname = new TextField();
    private final Label lblKundennummer = new Label("Kundennummer");
    private final TextField txtKundennummer = new TextField();
    private final Label lblHausnummer = new Label("HausNummer");
    private final TextField txtHausnummer = new TextField();
    private final Label lblNachname = new Label("Nachname");
    private final TextField txtNachname = new TextField();
    private final Label lblTelefonnummer = new Label("Telefonnummer");
    private final TextField txtTelefonnummer = new TextField();
    private final Label lblEmail = new Label("E-Mail Adresse");
    private final TextField txtEmail = new TextField();
    private final Button btnAnlegen = new Button("Anlegen");
    private final Button btnAendern = new Button("Ändern");
    private final Button btnLoeschen = new Button("Löschen");
    private final MenuBar mnBar = new MenuBar();
    // SOnderwuensche
    private final Menu mnSonderwuensche = new Menu("Sonderwünsche");
    private final MenuItem mnItmAussenanlage = new MenuItem("Aussenanlagevarianten");
    private final MenuItem mnItmFliesen = new MenuItem("Fliesenvarianten");
    private final MenuItem mnItmInnentueren = new MenuItem("Innentuervarianten");
    private final MenuItem mnItmParkett = new MenuItem("Parkettvarianten");
    private final MenuItem mnItmFensterAussentuer = new MenuItem("Fenster-Aussentuervarianten");
    private final MenuItem mnItmSanitaer = new MenuItem("Sanitaer-Aussentuervarianten");
    private final MenuItem mnItmGrundriss = new MenuItem("Grundriss-Aussentuervarianten");
    private final MenuItem mnItmHeizungen = new MenuItem("Heizungen-Aussentuervarianten");

    //-------Ende Attribute der grafischen Oberflaeche-------

    /**
     * erzeugt ein KundeView-Objekt und initialisiert die Steuerelemente der Maske
     *
     * @param kundeControl KundeControl, enthaelt das zugehoerige Control
     * @param primaryStage Stage, enthaelt das Stage-Objekt fuer diese View
     * @param kundeModel   KundeModel, enthaelt das zugehoerige Model
     */
    public KundeView(KundeControl kundeControl, Stage primaryStage, KundeModel kundeModel) {
        this.kundeControl = kundeControl;
        this.kundeModel = kundeModel;

        primaryStage.setTitle(this.kundeModel.getUeberschrift());
        Scene scene = new Scene(borderPane, 550, 400);
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
        gridPane.add(lblKundennummer, 0, 3);
        gridPane.add(txtKundennummer, 1, 3);
        //txtKundennummer.setPromptText("Kundennummer eingeben");
        gridPane.add(lblVorname, 0, 4);
        gridPane.add(txtVorname, 1, 4);
        gridPane.add(lblNachname, 0, 5);
        gridPane.add(txtNachname, 1, 5);
        gridPane.add(lblTelefonnummer, 0, 6);
        gridPane.add(txtTelefonnummer, 1, 6);
        gridPane.add(lblEmail, 0, 7);
        gridPane.add(txtEmail, 1, 7);
        gridPane.add(lblHausnummer, 0, 8);
        gridPane.add(txtHausnummer, 1, 8);
        // Buttons
        gridPane.add(btnAnlegen, 0, 10);
        btnAnlegen.setMinSize(150, 25);
        gridPane.add(btnAendern, 1, 10);
        btnAendern.setMinSize(150, 25);
        gridPane.add(btnLoeschen, 2, 10);
        btnLoeschen.setMinSize(150, 25);
        // MenuBar und Menu
        borderPane.setTop(mnBar);
        mnBar.getMenus().add(mnSonderwuensche);
        mnSonderwuensche.getItems().add(mnItmAussenanlage);
        mnSonderwuensche.getItems().add(mnItmFliesen);
        mnSonderwuensche.getItems().add(mnItmInnentueren);
        mnSonderwuensche.getItems().add(mnItmParkett);
        mnSonderwuensche.getItems().add(mnItmFensterAussentuer);
        mnSonderwuensche.getItems().add(mnItmSanitaer);
        mnSonderwuensche.getItems().add(mnItmGrundriss);
        mnSonderwuensche.getItems().add(mnItmHeizungen);

    }

    /* initialisiert die Listener zu den Steuerelementen auf der Maske */
    private void initListener() {

        cmbBxNummerHaus.setOnAction(event -> {
            leseKunden();
            holeInfoDachgeschoss();
        });

        btnAnlegen.setOnAction(aEvent -> legeKundenAn());

        btnAendern.setOnAction(aEvent -> aendereKunden());

        btnLoeschen.setOnAction(aEvent -> {
            int plannummer = cmbBxNummerHaus.getValue();
            loescheKunde(plannummer);
        });

        mnItmAussenanlage.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneAussenanlageControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mnItmFliesen.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneFliesenControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mnItmInnentueren.setOnAction(aEvent -> {
               try {
                    kundeControl.oeffneInnentuerenControl();
                } catch (NullPointerException e2){
                   zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        mnItmFensterAussentuer.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneFensterAussentuerControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mnItmParkett.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneParkettControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mnItmSanitaer.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneSanitaerControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mnItmGrundriss.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneGrundrissControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mnItmHeizungen.setOnAction(aEvent -> {
            try {
                kundeControl.oeffneHeizungenControl();
            } catch (NullPointerException e2){
                zeigeFehlermeldung("Kein Kunde","Wähl Kunden aus du OTTO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private boolean holeInfoDachgeschoss() {
        int plannummer = cmbBxNummerHaus.getValue();
        switch (plannummer){
            case 1:
            case 6:
            case 7:
            case 14:
            case 15:
            case 24:
                    System.out.println("Kein Dach");
                    return false;
            default:
                System.out.println("Ein Dach");
                return true;
        }
    }

    private void leseKunden() {
        int plannummer = cmbBxNummerHaus.getValue();
        kundeControl.loadKundeByPlannummer(plannummer);
    }

    private void legeKundenAn() {
        Kunde kunde = null;
        // Objekt kunde fuellen
        String vorname = txtVorname.getText();
        String nachname= txtNachname.getText();
        String email= txtEmail.getText();
        int kundenNr = Integer.parseInt(txtKundennummer.getText());
        int hausNr = Integer.parseInt(txtHausnummer.getText());
        String tel = txtTelefonnummer.getText();
        int planNr = cmbBxNummerHaus.getValue();
        KundeEntity kundeEntity = new KundeEntity(kundenNr,vorname,nachname,tel,email,planNr,hausNr);
        kunde = new Kunde(kundeEntity);
        kundeControl.speichereKunden(kunde);

    }

    private void aendereKunden() {
        Kunde kunde = null;
        String vorname = txtVorname.getText();
        String nachname= txtNachname.getText();
        String email= txtEmail.getText();
        int kundenNr = Integer.parseInt(txtKundennummer.getText());
        int hausNr = Integer.parseInt(txtHausnummer.getText());
        String tel = txtTelefonnummer.getText();
        int planNr = cmbBxNummerHaus.getValue();
        KundeEntity kundeEntity = new KundeEntity(kundenNr,vorname,nachname,tel,email,planNr,hausNr);
        kunde = new Kunde(kundeEntity);
        kundeControl.aendereKunden(kunde);


    }

    private void loescheKunde(int plannummer) {
        kundeControl.loescheKunde(plannummer);
        txtVorname.setText("");
        txtNachname.setText("");
        txtKundennummer.setText("");
        txtTelefonnummer.setText("");
        txtEmail.setText("");
        txtHausnummer.setText("");
    }

    public void setKundeDaten(Kunde kunde) {
        if (kunde != null) {
            txtVorname.setText(kunde.getVorname());
            txtEmail.setText(kunde.getEmail());
            txtKundennummer.setText(""+kunde.getKundennummer());
            txtTelefonnummer.setText(""+kunde.getTelefonnummer());
            txtNachname.setText(""+kunde.getNachname());
            txtHausnummer.setText(""+ kunde.getHausnummer());

        } else {
            txtVorname.setText("");
            txtNachname.setText("");
            txtKundennummer.setText("");
            txtTelefonnummer.setText("");
            txtEmail.setText("");
            txtHausnummer.setText("");
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
    public void zeigeMeldung(String ueberschrift, String meldung) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(ueberschrift);
        alert.setContentText(meldung);
        alert.show();

    }

}


