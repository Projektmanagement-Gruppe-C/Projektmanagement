package business.kunde;

import business.datenbank.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KundeDao {
    private static KundeDao instance;
    private final Datenbank datenbank;

    public void speichereKundeByButton(Kunde kunde) throws SQLException {
        int resultSet = datenbank.executeUpdate("INSERT INTO Kunde VALUES (\'" + kunde.getKundennummer() + "\', \'" + kunde.getNachname() + "\', \'" + kunde.getVorname() +"\', \'" + kunde.getTelefonnummer() + "\', \'" + kunde.getEmail() + "\', \'" + kunde.getPlannummer() + "\', \'" + kunde.getHausnummer() + "\')");
        System.out.println(resultSet);
    }

    public KundeEntity getKundeByPlanNr(int planNr) {

        try (ResultSet resultSet = datenbank.executeQuery("SELECT * FROM Kunde WHERE planNr = " + planNr)) {
            if (resultSet.next()) {
                return new KundeEntity(
                        resultSet.getInt("KundeID"),
                        resultSet.getString("Vorname"),
                        resultSet.getString("Nachname"),
                        resultSet.getString("TelefonNr"),
                        resultSet.getString("Email"),
                        resultSet.getInt("PlanNr"),
                        resultSet.getInt("HausNr")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void aendereKunden(Kunde kunde) throws SQLException {
         int resultSet  = datenbank.executeUpdate("UPDATE Kunde SET Nachname =  \'" + kunde.getNachname() + "\' , Email=\'" + kunde.getEmail()+"\' , Vorname=\'" + kunde.getVorname()+"\' , TelefonNr=\'" + kunde.getTelefonnummer()+"\' , HausNr=\'" + kunde.getHausnummer()+"\' , PlanNr=\'" + kunde.getPlannummer() +"\' , KundeID=\'" + kunde.getKundennummer()+"\' WHERE PlanNr= \'"+ kunde.getPlannummer() +"\' ;" );
        System.out.println(resultSet);
    }


    public void loescheKundeByButton(int planNr) throws SQLException {
        int resultSet = datenbank.executeUpdate("DELETE FROM Kunde WHERE PlanNr=\'"+planNr+"\'");
        System.out.println(resultSet);
    }

    private KundeDao(
            Datenbank datenbank
    ) {
        this.datenbank = datenbank;
    }

    public static KundeDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new KundeDao(Datenbank.getInstance());
        }
        return instance;
    }

    public void setKundeByButton() {
    }
}
