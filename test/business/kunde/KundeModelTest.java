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

import java.lang.reflect.Field;
import java.util.List;

public class KundeModelTest {
    private KundeDao kundeDao;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private List<KundeEntity> mockKunden;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        kundeDao = Mockito.mock(KundeDao.class);
        setMock(kundeDao);

        KundeEntity mockKunde = new KundeEntity();
        mockKunde.setVorname("Max");
        mockKunde.setNachname("Mustermann");
        mockKunde.setEmail("max@mustermann.de");
        mockKunde.setTelefonnummer("0123456789");

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
    public void testGetAllKunden() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(kundeDao.getAllKunden()).thenReturn(mockKunden);
        KundeModel kundeModel = KundeModel.getInstance();
        kundeModel.loadKunden();

        Mockito.verify(kundeDao, Mockito.times(1)).getAllKunden();

        // Verify that the kundeModel contains the mockKunde
        Assertions.assertEquals(mockKunden, kundeModel.getKunden());
    }
}

