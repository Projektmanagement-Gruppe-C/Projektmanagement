package business.grundriss;

import business.IValidierung;
import business.kunde.KundeEntity;
import business.kunde.Kunde;
import business.kunde.KundeDao;
import business.kunde.KundeModel;
import gui.kunde.KundeControl;
import gui.kunde.KundeView;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import gui.grundriss.GrundrissView;

import java.sql.SQLException;
import java.util.Arrays;

public class Grundriss implements IValidierung {
    private int id;
    private String beschreibung;
    private double preis;

    public Grundriss() {

    }

    private KundeModel kundeModel;
    private GrundrissView grundrissView;

    public Grundriss(GrundrissEntity entity) throws SQLException, ClassNotFoundException {
        this.id = entity.getId();
        this.beschreibung = entity.getBeschreibung();
        this.preis = entity.getPreis();
        //this.kundeModel= KundeModel.getInstance();
        //this.grundrissView= grundrissView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Grundriss{" +
                "id=" + id +
                ", beschreibung='" + beschreibung + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean istValide() {
        //TODO

        int plannummer = kundeModel.getPlanNr();
        CheckBox[] checkBoxArray = grundrissView.getCheckBoxArray();
        int[] EG = {1,6,7,14,15,24};

        if(Arrays.asList(EG).contains(plannummer)) {
            //EG
            if(checkBoxArray[3].isSelected()) {
                checkBoxArray[3].setDisable(true);
            }

            if(checkBoxArray[4].isSelected()) {
                checkBoxArray[4].setDisable(true);
            }

            if(checkBoxArray[5].isSelected()) {
                checkBoxArray[5].setDisable(true);
            }
        }

        return false;
    }
}
