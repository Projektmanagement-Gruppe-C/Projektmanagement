package gui.parkett;


import business.kunde.Kunde;
import business.kunde.KundeModel;
import business.parkett.ParkettModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class ParkettControl implements PropertyChangeListener {

    // das View-Objekt des Grundriss-Fensters
    private ParkettView parkettView;

    private ParkettModel parkettModel;

   //TODO
    private KundeModel kundeModel;


    public ParkettControl() throws Exception {
        Stage stageFliesen = new Stage();
        stageFliesen.initModality(Modality.APPLICATION_MODAL);
        this.parkettModel= parkettModel.getInstance();
        this.parkettView = new ParkettView(this, stageFliesen, this.parkettModel);
        this.kundeModel = KundeModel.getInstance();
        this.parkettModel.addPropertyChangeListener(this);
    }

    public void oeffneParkettView(){
        this.parkettView.oeffneParkettView();
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
        if (propertyName.equals(ParkettModel.PARKETT_PROPERTY)){
            //TODO
        }
    }

    public void test() throws SQLException, ClassNotFoundException {
        List<Integer>la = connectKunde();
        Kunde kunde = kundeModel.getInstance().getKunde();
       // fliesenModel.getFliesen().istValide(kunde,la);
    }

// NIMMT SICH von KUndenmodel kunde (getKundennummer)und verbindet es mit Fliese
    //GIbt fliesenliste von aktuellen Kunden

    public List<Integer> connectKunde() throws SQLException, ClassNotFoundException {
        Kunde kunde= kundeModel.getInstance().getKunde();
        List<Integer>la = parkettModel.loadParkettListe(kunde.getKundennummer());
        return la;
    }

    public void speichereSonderwunsch(int sid) throws SQLException, ClassNotFoundException {
        int kid = kundeModel.getKunde().getKundennummer();
        List<Integer> li = connectKunde();
            if(!li.contains(sid))
                parkettModel.speichereSonderwuensche(sid,kid);
    }

    public void loescheSonderwuensche() throws SQLException {
        int kid = kundeModel.getKunde().getKundennummer();
        parkettModel.loescheSonderwuensche(kid);
    }
}



