package gui.sanitaer;


import business.fenster_aussentuer.FensterAussentuerModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import business.sanitaer.SanitaerModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class SanitaerControl implements PropertyChangeListener {

    // das View-Objekt des Grundriss-Fensters
    private SanitaerView view;

    private SanitaerModel model;

    private KundeModel kundeModel;


    public SanitaerControl() throws Exception {
        Stage stageAussenanlage = new Stage();
        stageAussenanlage.initModality(Modality.APPLICATION_MODAL);
        this.model = SanitaerModel.getInstance();
        this.view = new SanitaerView(this, stageAussenanlage, this.model);
        this.kundeModel = KundeModel.getInstance();
        this.model.addPropertyChangeListener(this);
    }

    public void oeffneSanitaerView(){
        this.view.oeffneSanitaerView();
    }


    public void hatDachgeschoss() {
        //TODO

    }

    public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
        //TODO
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals(SanitaerModel.SANITAER_PROPERTY)){

        }
    }

    public void test() throws SQLException, ClassNotFoundException {
        List<Integer>la = connectKunde();
        Kunde kunde = kundeModel.getInstance().getKunde();
       // aussenanlageModel.getAussenanlagen().istValide(kunde,la);
    }

    public List<Integer> connectKunde() throws SQLException, ClassNotFoundException {
        Kunde kunde= kundeModel.getInstance().getKunde();
        List<Integer>la = model.loadSanitaerListe(kunde.getKundennummer());
        return la;
    }

    public void speichereSonderwunsch(int sid) throws SQLException, ClassNotFoundException {
        int kid = kundeModel.getKunde().getKundennummer();
        List<Integer> li = connectKunde();
        if(!li.contains(sid))
            model.speichereSonderwuensche(sid,kid);
    }

    public void loescheSonderwuensche() throws SQLException {
        int kid = kundeModel.getKunde().getKundennummer();
        model.loescheSonderwuensche(kid);
    }
}



