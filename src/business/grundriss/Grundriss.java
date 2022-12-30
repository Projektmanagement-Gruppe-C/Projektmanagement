package business.grundriss;

import business.IValidierung;

import java.util.Arrays;

public class Grundriss implements IValidierung {

    private int id;
    private String beschreibung;
    private double preis;

    private Grundriss() {
    }

    public Grundriss(GrundrissEntity entity) {
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
        return "Grundriss{" +
                "id=" + id +
                ", beschreibung='" + beschreibung + '\'' +
                ", preis=" + preis +
                '}';
    }


    @Override
    public boolean istValide() {
        // TODO
           /*int plannummer = 0;
        boolean[] checkbox_List = {true, false, true, false, false, false};
        int[] plannummer_EG = {1, 6, 7, 14, 15};
        int[] plannummer_OG = {2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20, 21, 22, 23, 24};

        if (Arrays.asList(plannummer_EG).contains(plannummer)) {
            if (checkbox_List[4] == true || checkbox_List[5] == true || checkbox_List[6] == true) {
                return false;
            } else if (checkbox_List[2] == true && checkbox_List[1] == false) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


        if (Arrays.asList(plannummer_OG).contains(plannummer)) {
            if (checkbox_List[2] == true && checkbox_List[1] == false) {
                return false;
            } else if (checkbox_List[6] == true && checkbox_List[5] == false) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
        */
    return false;
    }
}
