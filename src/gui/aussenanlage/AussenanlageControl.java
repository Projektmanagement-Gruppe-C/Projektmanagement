package gui.aussenanlage;

import business.aussenanlage.AussenanlageModel;

import business.aussenanlage.AussenanlageModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class AussenanlageControl implements PropertyChangeListener {

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
        this.aussenanlageModel.addPropertyChangeListener(this);
    }

    public void oeffneAussenanlageView(){
        this.aussenanlageView.oeffneAussenanlageView();
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

    public void schreibeInCsv(){
        this.aussenanlageModel.schreibeInCsv();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals(AussenanlageModel.AUSSENANLAGE_PROPERTY)){


        }
    }

    public void test() throws SQLException, ClassNotFoundException {
        List<Integer>la = connectKunde();
        Kunde kunde = kundeModel.getInstance().getKunde();
       // aussenanlageModel.getAussenanlagen().istValide(kunde,la);
    }

    public List<Integer> connectKunde() throws SQLException, ClassNotFoundException {
        Kunde kunde= kundeModel.getInstance().getKunde();
        List<Integer>la = aussenanlageModel.loadAussenanlagenListe(kunde.getKundennummer());
        return la;
    }

    public void speichereSonderwunsch(int sid) throws SQLException, ClassNotFoundException {
        int kid = kundeModel.getKunde().getKundennummer();
        List<Integer> li = connectKunde();
        if(!li.contains(sid))
            aussenanlageModel.speichereSonderwuensche(sid,kid);
    }

    public void loescheSonderwuensche() throws SQLException {
        int kid = kundeModel.getKunde().getKundennummer();
        aussenanlageModel.loescheSonderwuensche(kid);
    }
}



