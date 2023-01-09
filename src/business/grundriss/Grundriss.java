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

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getPreis() {
        return preis;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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
    public String toString2(List<Integer> chcks) {
        String s = "";

            for (int i : chcks)
                if (id == i)
                    s += "Grundriss{" +
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
