package business.kunde;

public class KundeEntity {
    public int getKundeID() {
        return kundeID;
    }

    public void setKundeID(int kundeID) {
        this.kundeID = kundeID;
    }

    private int kundeID;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String vorname;
    private String nachname;
    private String telefonnummer;
    private String email;

    private int plannummer;

    public int getPlannummer() {
        return plannummer;
    }

    public void setPlannummer(int plannummer) {
        this.plannummer = plannummer;
    }

    public KundeEntity() {
    }

    public KundeEntity(int kundeID, String vorname, String nachname, String telefonnummer, String email, int plannummer) {
        this.kundeID = kundeID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.plannummer = plannummer;
    }
}
