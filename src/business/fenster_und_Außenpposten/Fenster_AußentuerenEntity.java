package business.fenster_und_Außenpposten;

public class Fenster_AußentuerenEntity {

    private int id;
    private String beschreibung;
    private double preis;

    public Fenster_AußentuerenEntity() {
    }

    public Fenster_AußentuerenEntity(int id, String beschreibung, double preis) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.preis = preis;
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
}
