package business.parkett;

public class ParkettEntity {

    private int id;
    private String beschreibung;
    private double preis;

    public ParkettEntity() {
    }

    public ParkettEntity(int id, String beschreibung, double preis) {
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
