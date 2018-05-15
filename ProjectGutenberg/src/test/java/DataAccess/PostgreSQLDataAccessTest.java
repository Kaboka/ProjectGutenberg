package DataAccess;

import Connectors.PostgreSQLConnector;
import java.sql.Connection;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostgreSQLDataAccessTest {

    @Mock
    private PostgreSQLConnector dbConnection;
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockDBConnection() throws Exception {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
        int value = dbConnection.executeQuery("");
        Assert.assertEquals(1, value);
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
    }

    //@Test
//    public void testGetByCityName() throws SQLException {
//        String cityName = "London";
//        Mockito.when(con.createStatement()).thenReturn(stmt);
//        Mockito.when(stmt.executeQuery("SELECT book_title, author_name FROM books WHERE cityName = " + cityName)).thenReturn(resultSet);
//        int conValue = con.createStatement().executeUpdate("SELECT book_name, author FROM books WHERE cityName = " + cityName);
//
//        ResultSet value = stmt.executeQuery("SELECT book_name, author FROM books WHERE cityName = " + cityName);
//        assertEquals(value, resultSet);
//        assertEquals(conValue, 1);
//        // Mockito.verify(con, Mockito.times(1));
//    }
}
