package business.kunde;

import business.IValidierung;

public class Kunde implements IValidierung {

	private int id;
	private int hausnummer;
	private String vorname;
	private String nachname;
	private String telefonnummer;
	private String email;

	public int getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(int kundennummer) {
		this.kundennummer = kundennummer;
	}

	private int kundennummer;
		  
	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}

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

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Kunde [kundennummer=" + kundennummer + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", telefonnummer=" + telefonnummer + ", email=" + email + "]";
	}

	Kunde() {}

	Kunde(
			KundeEntity kundeEntity
	) {
		this.id = kundeEntity.getKundeID();
		this.kundennummer = kundeEntity.getKundeID();
		this.vorname = kundeEntity.getVorname();
		this.nachname = kundeEntity.getNachname();
		this.telefonnummer = kundeEntity.getTelefonnummer();
		this.email = kundeEntity.getEmail();
		this.hausnummer = kundeEntity.getPlannummer();
	}

	@Override
	public boolean istValide() {

		if((!getNachname().isBlank()) && ( (getTelefonnummer() != null && getTelefonnummer().matches("[0-9]+")) || ((getEmail() != null) && getEmail().contains("@") ) )  && ( (getHausnummer() >= 1) && ( getHausnummer() <= 24 ) ) )

		return true;
		else
			return false;

	}
}
