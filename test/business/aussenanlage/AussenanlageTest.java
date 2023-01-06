package business.aussenanlage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AussenanlageTest {

    @Test
    public void istValide_returnsTrue() {
        Aussenanlage aussenanlagen = new Aussenanlage();
        Aussenanlage elektrischeMarkiseEG = new Aussenanlage(new AussenanlageEntity(1, "Elektrische Markise EG", 100.00));
        Aussenanlage vorbereitungMarkiseEG = new Aussenanlage(new AussenanlageEntity(2, "Vorbereitung für elektrische Antriebe Markise EG", 100.00));
        Aussenanlage elektrischeMarkiseDG = new Aussenanlage(new AussenanlageEntity(3, "Elektrische Markise DG", 100.00));
        Aussenanlage vorbereitungMarkiseDG = new Aussenanlage(new AussenanlageEntity(4, "Vorbereitung für elektrische Antriebe Markise DG", 100.00));
        Aussenanlage sektionalTor = new Aussenanlage(new AussenanlageEntity(5, "Sektionaltor anstatt Schwingtor für die Garage", 100.00));
        Aussenanlage elektrischerAntrieb = new Aussenanlage(new AussenanlageEntity(1, "Elektrischen Antrieb für das Garagentor", 100.00));

        aussenanlagen.addAnlagen(elektrischeMarkiseEG);
        aussenanlagen.addAnlagen(vorbereitungMarkiseEG);
        aussenanlagen.addAnlagen(elektrischeMarkiseDG);
        aussenanlagen.addAnlagen(vorbereitungMarkiseDG);
        aussenanlagen.addAnlagen(sektionalTor);
        aussenanlagen.addAnlagen(elektrischerAntrieb);

        boolean result = aussenanlagen.istValide();

        assertTrue(result);
    }

    @Test
    public void istValide_elektrischeMarkiseEGOhneVorbereitung_returnsfalse() {
        Aussenanlage aussenanlagen = new Aussenanlage();
        Aussenanlage elektrischeMarkiseEG = new Aussenanlage(new AussenanlageEntity(1, "Elektrische Markise EG", 100.00));

        aussenanlagen.addAnlagen(elektrischeMarkiseEG);

        boolean result = aussenanlagen.istValide();

        assertFalse(result);
    }

    @Test
    public void istValide_elektrischeMarkiseDGOhneVorbereitung_returnsFalse() {
        Aussenanlage aussenanlagen = new Aussenanlage();

        Aussenanlage elektrischeMarkiseEG = new Aussenanlage(new AussenanlageEntity(1, "Elektrische Markise EG", 100.00));
        Aussenanlage vorbereitungMarkiseEG = new Aussenanlage(new AussenanlageEntity(2, "Vorbereitung für elektrische Antriebe Markise EG", 100.00));
        Aussenanlage elektrischeMarkiseDG = new Aussenanlage(new AussenanlageEntity(3, "Elektrische Markise DG", 100.00));

        aussenanlagen.addAnlagen(elektrischeMarkiseEG);
        aussenanlagen.addAnlagen(vorbereitungMarkiseEG);
        aussenanlagen.addAnlagen(elektrischeMarkiseDG);

        boolean result = aussenanlagen.istValide();

        assertFalse(result);
    }

    @Test
    public void istValide_sektionalTorOhneElektrischenAntrieb_returnsFalse() {
        Aussenanlage aussenanlagen = new Aussenanlage();
        Aussenanlage elektrischeMarkiseEG = new Aussenanlage(new AussenanlageEntity(1, "Elektrische Markise EG", 100.00));
        Aussenanlage vorbereitungMarkiseEG = new Aussenanlage(new AussenanlageEntity(2, "Vorbereitung für elektrische Antriebe Markise EG", 100.00));
        Aussenanlage elektrischeMarkiseDG = new Aussenanlage(new AussenanlageEntity(3, "Elektrische Markise DG", 100.00));
        Aussenanlage vorbereitungMarkiseDG = new Aussenanlage(new AussenanlageEntity(4, "Vorbereitung für elektrische Antriebe Markise DG", 100.00));
        Aussenanlage sektionalTor = new Aussenanlage(new AussenanlageEntity(5, "Sektionaltor anstatt Schwingtor für die Garage", 100.00));

        aussenanlagen.addAnlagen(elektrischeMarkiseEG);
        aussenanlagen.addAnlagen(vorbereitungMarkiseEG);
        aussenanlagen.addAnlagen(elektrischeMarkiseDG);
        aussenanlagen.addAnlagen(vorbereitungMarkiseDG);
        aussenanlagen.addAnlagen(sektionalTor);

        boolean result = aussenanlagen.istValide();

        assertFalse(result);
    }
}
