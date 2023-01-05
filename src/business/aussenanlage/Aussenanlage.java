package business.aussenanlage;

import business.IValidierung;

public class Aussenanlage implements IValidierung {

    private int id;
    private String beschreibung;
    private double preis;

    private Aussenanlage() {
    }

    public  Aussenanlage(AussenanlageEntity entity) {
        this.id = entity.getId();
        this.beschreibung = entity.getBeschreibung();
        this.preis = entity.getPreis();
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
        return

                " beschreibung= '" + beschreibung + '\'' +"    "+
                ", preis= " + preis
                ;
    }

    @Override
    public boolean istValide() {
        // TODO
        return false;
    }
}
