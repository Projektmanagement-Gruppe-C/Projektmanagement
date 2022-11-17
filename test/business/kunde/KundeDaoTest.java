package business.kunde;

import business.datenbank.Datenbank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentMatchers;

import java.sql.ResultSet;

public class KundeDaoTest {


    KundeDao kundeDaoTest;
    Kunde kunde;
    Datenbank db;
    KundeEntity mockKunde;

    @BeforeEach
    public void SetUp(){

        MockitoAnnotations.openMocks(this);

        mockKunde = new KundeEntity();
        mockKunde.setVorname("Max");
        mockKunde.setNachname("Mustermann");
        mockKunde.setEmail("max@mustermann.de");
        mockKunde.setTelefonnummer("0123456789");
        mockKunde.setPlannummer(20);

        kunde = new Kunde();
        kunde.setHausnummer(2);
        kunde.setNachname("fpsdj");
        kunde.setKundennummer(2);
        kunde.setEmail("mes@sa.de");
        kunde.setVorname("Test");

        db = Mockito.mock(Datenbank.class);

    }

   /* @Test

   public void testGetKundeByHausnummer() throws Exception{
//TODO

      //ResultSet resultSet = new ResultSet(){
          
      };


        // Mock the DB response
        Mockito.when(db.executeQuery(ArgumentMatchers.anyString())).thenReturn(mockKunde);


    }
*/

}
