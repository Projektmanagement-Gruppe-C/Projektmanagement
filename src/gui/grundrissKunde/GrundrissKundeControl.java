package gui.grundrissKunde;

import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class GrundrissKundeControl {

    // das View-Objekt des Grundriss-Fensters
    private final GrundrissKundeView grundrissKundeView;

    public GrundrissKundeControl(KundeModel kundeModel){
        Stage stageGrundrissKunde = new Stage();
        stageGrundrissKunde.initModality(Modality.APPLICATION_MODAL);
        grundrissKundeView = new GrundrissKundeView(stageGrundrissKunde);
    }

    /**
     * macht das GrundrissView-Objekt sichtbar.
     */
    public void oeffneGrundrissKundeView(){
        this.grundrissKundeView.oeffneBasisView();
    }
}