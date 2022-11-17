package business.kunde;


import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.hamcrest.MatcherAssert;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.Matchers.samePropertyValuesAs;

public class KundeModelTest {
    // Test Property Change Listener
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        public String propertyName;
        public Object oldValue;
        public Object newValue;

        @Override
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            propertyName = evt.getPropertyName();
            oldValue = evt.getOldValue();
            newValue = evt.getNewValue();
        }
    }

    private KundeDao kundeDao;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private List<KundeEntity> mockKunden;
    private final KundeEntity mockKunde = new KundeEntity();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        kundeDao = Mockito.mock(KundeDao.class);
        setMock(kundeDao);


        mockKunde.setVorname("Max");
        mockKunde.setNachname("Mustermann");
        mockKunde.setEmail("max@mustermann.de");
        mockKunde.setTelefonnummer("0123456789");
        mockKunde.setPlannummer(1);
        mockKunde.setHausnr(20);

        mockKunden = List.of(mockKunde);

    }

    @AfterEach
    public void tearDown() throws Exception {
        Field instance = KundeDao.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    private void setMock(KundeDao mock) {
        try {
            Field instance = KundeDao.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetKundeByPlanNr() throws Exception {
        // Mock the DAO response
        Mockito.when(kundeDao.getKundeByPlanNr(1)).thenReturn(mockKunde);

        // Create a test listener
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        // Verify the property listener update method is called
        KundeModel kundeModel = KundeModel.getInstance();

        // Add the listener
        kundeModel.addPropertyChangeListener(listener);

        // Load Kunde
        kundeModel.loadKundeByPlannummer(1);

        // Verify the listener was called
        Assertions.assertEquals(KundeModel.KUNDE_PROPERTY, listener.propertyName);
        Assertions.assertNull(listener.oldValue);
        Kunde kunde = new Kunde(mockKunde);
        // Assert that all fields are equal
        Assertions.assertEquals(kunde.getVorname(), ((Kunde) listener.newValue).getVorname());
        Assertions.assertEquals(kunde.getNachname(), ((Kunde) listener.newValue).getNachname());
        Assertions.assertEquals(kunde.getEmail(), ((Kunde) listener.newValue).getEmail());
        Assertions.assertEquals(kunde.getTelefonnummer(), ((Kunde) listener.newValue).getTelefonnummer());
        MatcherAssert.assertThat(kunde, samePropertyValuesAs((Kunde) listener.newValue));
    }
}

