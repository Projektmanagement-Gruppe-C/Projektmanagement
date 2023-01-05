package gui.fliesen;


import business.fliesen.FliesenModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class FliesenControl implements PropertyChangeListener {

    // das View-Objekt des Grundriss-Fensters
    private FliesenView fliesenView;

    private FliesenModel fliesenModel;

   //TODO
    private KundeModel kundeModel;


    public FliesenControl() throws Exception {
        Stage stageFliesen = new Stage();
        stageFliesen.initModality(Modality.APPLICATION_MODAL);
        this.fliesenModel= FliesenModel.getInstance();
        this.fliesenView = new FliesenView(this, stageFliesen, this.fliesenModel);
        this.kundeModel = KundeModel.getInstance();
        this.fliesenModel.addPropertyChangeListener(this);
    }

    public void oeffneFliesenView(){
        this.fliesenView.oeffneFliesenView();
    }

    /*public void leseAussenanlageSonderwuensche(){
        //TODO
        int p= kundeModel.getKunde().getPlannummer();
        int i= kundeModel.getKunde().getKundennummer();

    }*/

    public boolean hatDachgeschoss() {
        switch (kundeModel.getKunde().getPlannummer()){
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

    public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
        //TODO
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals(FliesenModel.FLIESEN_PROPERTY)){


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
        List<Integer>la = fliesenModel.loadFliesenListe(kunde.getKundennummer());
        return la;
    }

    public void speichereSonderwunsch(int sid) throws SQLException, ClassNotFoundException {
        int kid = kundeModel.getKunde().getKundennummer();
        List<Integer> li = connectKunde();
            if(!li.contains(sid))
                fliesenModel.speichereSonderwuensche(sid,kid);
    }

    public void loescheSonderwuensche() throws SQLException {
        int kid = kundeModel.getKunde().getKundennummer();
        fliesenModel.loescheSonderwuensche(kid);
    }
}



