package gui.aussenanlage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MeldungsfensterAnzeiger {

    private AlertType alertTyp;
    private String titel;
    private String meldung;

    public MeldungsfensterAnzeiger(AlertType alertTyp, String titel, String meldung){
        this.alertTyp = alertTyp;
        this.titel = titel;
        this.meldung = meldung;
        if(meldung == null || "".equals(meldung)){
            this.meldung = "Die Meldung ist nicht vorhanden.";
        }
    }

    public void zeigeMeldungsfensterAn(){
        Alert alert = new Alert(alertTyp);
        alert.setTitle(this.titel);
        alert.setContentText(this.meldung);
        alert.showAndWait();
    }

}