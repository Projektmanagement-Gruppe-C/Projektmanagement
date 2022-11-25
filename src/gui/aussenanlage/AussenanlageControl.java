package gui.aussenanlage;


import business.aussenanlage.AussenanlageModel;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AussenanlageControl {

    // das View-Objekt des Grundriss-Fensters
    private AussenanlageView aussenanlageView;

    private AussenanlageModel aussenanlageModel;
    private KundeModel kundeModel;


    public AussenanlageControl() throws Exception {
        Stage stageAussenanlage = new Stage();
        stageAussenanlage.initModality(Modality.APPLICATION_MODAL);
        this.aussenanlageModel = AussenanlageModel.getInstance();
        this.aussenanlageView = new AussenanlageView(this, stageAussenanlage, this.aussenanlageModel);
        this.kundeModel = KundeModel.getInstance();
    }

    public void oeffneAussenanlageView(){
        this.aussenanlageView.oeffneAussenanlageView();
    }

    public void leseAussenanlageSonderwuensche(){
        //TODO

    }

    public void hatDachgeschoss() {
        //TODO

    }

    public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
        //TODO
        return true;
    }

}
