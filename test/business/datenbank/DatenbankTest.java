package business.datenbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.Statement;

public class DatenbankTest {
    @InjectMocks private Datenbank datenbank;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMockDBConnection() throws Exception {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.anyString())).thenReturn(1);
        int result = datenbank.executeQuery("");
        Assertions.assertEquals(1, result);
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1)).executeUpdate(Mockito.anyString());
    }
}
