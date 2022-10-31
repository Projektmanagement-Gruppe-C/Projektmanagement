package business.kunde;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KundeTest {

    @Test
    void testHausnummer() {
        Kunde kunde = new Kunde();
        kunde.setHausnummer(1);
        assertEquals(1, kunde.getHausnummer());
    }

    @Test
    void testVorname() {
        Kunde kunde = new Kunde();
        kunde.setVorname("Max");
        assertEquals("Max", kunde.getVorname());
    }
}
