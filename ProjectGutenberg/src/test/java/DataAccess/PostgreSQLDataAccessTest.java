
package DataAccess;

import Connectors.PostgreSQLConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class PostgreSQLDataAccessTest {

    PostgreSQLConnector connector = new PostgreSQLConnector();

    @Mock
    private Connection con;
    @Mock
    private Statement stmt;
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockDBConnection() throws SQLException {
        Mockito.when(con.createStatement()).thenReturn(stmt);
        Mockito.when(stmt.executeQuery("")).thenReturn(resultSet);
        stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("");
        assertEquals(res, resultSet);
        Mockito.verify(con).createStatement();
    }
    
    
}
