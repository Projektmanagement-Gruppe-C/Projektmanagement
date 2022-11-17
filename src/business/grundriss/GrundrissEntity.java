package business.grundriss;

public class GrundrissEntity {
    private final int id;
    private final String beschreibung;
    private final int preis;

    public GrundrissEntity(int id, String beschreibung, int preis) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.preis = preis;
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

    public Grundriss toGrundriss()
    {
        return new Grundriss(this);
    }
}
