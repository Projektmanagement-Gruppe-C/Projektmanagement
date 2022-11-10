package business.kunde;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class KundeTest {

    @Test
    void testHausnummer() {
        Kunde kunde = new Kunde();
        kunde.setHausnummer(1);
        assertEquals(1, kunde.getHausnummer());
    }


    @Test
    void testValide(){
    Kunde kunde = new Kunde();

    kunde.setNachname("Mustermann");
    kunde.setTelefonnummer("0151123456343");
    kunde.setEmail("");
    kunde.setHausnummer(23);

    assumeTrue(kunde.istValide());

    }
}