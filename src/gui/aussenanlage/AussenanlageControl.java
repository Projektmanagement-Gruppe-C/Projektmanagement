package gui.aussenanlage;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class AussenanlageControl {

    // das View-Objekt des Grundriss-Fensters
    private AussenanlageView aussenanlageView;

    public AussenanlageControl(){
        Stage stageAussenanlage = new Stage();
        stageAussenanlage.initModality(Modality.APPLICATION_MODAL);
        this.aussenanlageView = new AussenanlageView(this, stageAussenanlage);
    }


    public void oeffneAussenanlageView(){
        this.aussenanlageView.oeffneAussenanlageView();
    }

    public void leseAussenanlageSonderwuensche(){

    }

    public boolean hatDachgeschoss() {
        return true;
    }

    public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
        return true;
    }
}
