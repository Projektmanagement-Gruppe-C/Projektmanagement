package gui.fenster_aussentuer;


import business.fenster_aussentuer.FensterAussentuerModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class FensterAussentuerControl implements PropertyChangeListener {

    // das View-Objekt des Grundriss-Fensters
    private FensterAussentuerView view;

    private FensterAussentuerModel model;

    private KundeModel kundeModel;


    public FensterAussentuerControl() throws Exception {
        Stage stageAussenanlage = new Stage();
        stageAussenanlage.initModality(Modality.APPLICATION_MODAL);
        this.model = FensterAussentuerModel.getInstance();
        this.view = new FensterAussentuerView(this, stageAussenanlage, this.model);
        this.kundeModel = KundeModel.getInstance();
        this.model.addPropertyChangeListener(this);
    }

    public void oeffneFensterAussentuerView(){
        this.view.oeffneFensterAussentuerView();
    }

    /*public void leseAussenanlageSonderwuensche(){
        //TODO
        int p= kundeModel.getKunde().getPlannummer();
        int i= kundeModel.getKunde().getKundennummer();

    }*/

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
        if (propertyName.equals(FensterAussentuerModel.FENSTER_AUSSENTUER_PROPERTY)){


        }
    }

    public void test() throws SQLException, ClassNotFoundException {
        List<Integer>la = connectKunde();
        Kunde kunde = kundeModel.getInstance().getKunde();
       // aussenanlageModel.getAussenanlagen().istValide(kunde,la);
    }

    public List<Integer> connectKunde() throws SQLException, ClassNotFoundException {
        Kunde kunde= kundeModel.getInstance().getKunde();
        List<Integer>la = model.loadFensterAussentuerListe(kunde.getKundennummer());
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



