package business.aussenanlage;

import business.IValidierung;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Aussenanlage implements IValidierung {

    private List<Aussenanlage> anlagen = new ArrayList<>();

    private int id;
    private String beschreibung;
    private double preis;

    public Aussenanlage() {
    }

    public Aussenanlage(AussenanlageEntity entity) {
        this.id = entity.getId();
        this.beschreibung = entity.getBeschreibung();
        this.preis = entity.getPreis();
        this.anlagen.add(this);
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

    public void addAnlagen(Aussenanlage anlage) {
        this.anlagen.add(anlage);
    }

    @Override
    public String toString() {
        return "Aussenanlage{" +
                "id=" + id +
                ", beschreibung='" + beschreibung + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean istValide() {
        List<String> bezeichnungen = anlagen.stream()
                .map(anlage -> anlage.beschreibung.trim()).collect(Collectors.toList());

        if (bezeichnungen.contains("Elektrische Markise EG")
                && !bezeichnungen.contains("Vorbereitung für elektrische Antriebe Markise EG")) {
            return false;
        }
        if (bezeichnungen.contains("Elektrische Markise DG")
                && !bezeichnungen.contains("Vorbereitung für elektrische Antriebe Markise DG")) {
            return false;
        }
        return !bezeichnungen.contains("Sektionaltor anstatt Schwingtor für die Garage")
                || bezeichnungen.contains("Elektrischen Antrieb für das Garagentor");
    }
}
