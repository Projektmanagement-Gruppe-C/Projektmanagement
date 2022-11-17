package business.grundriss;

public class Grundriss {
    private int id;
    private String beschreibung;
    private int preis;

    public Grundriss(int id, String beschreibung, int preis) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.preis = preis;
    }

    public Grundriss(GrundrissEntity entity)
    {
        this.id = entity.getId();
        this.beschreibung = entity.getBeschreibung();
        this.preis = entity.getPreis();
    }

    public int getId() {
        return id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getPreis() {
        return preis;
    }
}
