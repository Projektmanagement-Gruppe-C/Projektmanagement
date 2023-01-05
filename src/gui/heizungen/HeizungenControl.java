package gui.heizungen;


import business.heizungen.HeizungenModel;
import business.kunde.Kunde;
import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class HeizungenControl implements PropertyChangeListener {

    // das View-Objekt des Heizungen-Fensters
    private HeizungenView view;

    private HeizungenModel heizungenModel;

   //TODO
    private KundeModel kundeModel;


    public HeizungenControl() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        this.heizungenModel= HeizungenModel.getInstance();
        this.view = new HeizungenView(this, stage, this.heizungenModel);
        this.kundeModel = KundeModel.getInstance();
        this.heizungenModel.addPropertyChangeListener(this);
    }

    public void oeffneHeizungenView(){
        this.view.oeffneHeizungenView();
    }

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

    public boolean validierung() {
        int x=0,y=0,z=0;

        List<Integer> listInnentueren = view.getChcks();

        return false;
    }

    public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
        //TODO
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals(HeizungenModel.HEIZUNGEN_PROPERTY)){

        }
    }

    public void test() throws SQLException, ClassNotFoundException {
        List<Integer>la = connectKunde();
        Kunde kunde = kundeModel.getInstance().getKunde();
    }

// NIMMT SICH von KUndenmodel kunde (getKundennummer)und verbindet es mit Fliese
    //GIbt fliesenliste von aktuellen Kunden

    public List<Integer> connectKunde() throws SQLException, ClassNotFoundException {
        Kunde kunde= kundeModel.getInstance().getKunde();
        List<Integer>la = heizungenModel.loadHeizungenListe(kunde.getKundennummer());
        return la;
    }

    public void speichereSonderwunsch(int sid) throws SQLException, ClassNotFoundException {
        int kid = kundeModel.getKunde().getKundennummer();
        List<Integer> li = connectKunde();
            if(!li.contains(sid))
                heizungenModel.speichereSonderwuensche(sid,kid);
    }

    public void loescheSonderwuensche() throws SQLException {
        int kid = kundeModel.getKunde().getKundennummer();
        heizungenModel.loescheSonderwuensche(kid);
    }
}



