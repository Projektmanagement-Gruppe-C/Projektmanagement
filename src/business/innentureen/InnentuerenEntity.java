package business.innentureen;

public class InnentuerenEntity {

    private int id;
    private String beschreibung;
    private double preis;

    public InnentuerenEntity() {
    }

    public InnentuerenEntity(int id, String beschreibung, double preis) {
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
