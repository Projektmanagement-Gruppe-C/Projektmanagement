package business.innentueren;

import business.IValidierung;

import java.util.List;

public class Innentueren implements IValidierung {

    private int id;
    private String beschreibung;
    private double preis;

    private Innentueren() {
    }

    public Innentueren(InnentuerenEntity entity) {
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
        return "Innentueren{" +
                "id=" + id +
                ", beschreibung='" + beschreibung + '\'' +
                ", preis=" + preis +
                '}';
    }
    public String toString2(List<Integer> chcks) {
        String s = "";

        for (int i : chcks)
            if (id == i)
                s += "Innentueren{" +
                        "id=" + id +
                        ", beschreibung='" + beschreibung + '\'' +
                        ", preis=" + preis +
                        '}';


        return  s;

    }

    @Override
    public boolean istValide() {
        // TODO
        return false;
    }
}
