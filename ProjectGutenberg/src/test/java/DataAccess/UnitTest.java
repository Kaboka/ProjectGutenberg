package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.Assert;
import static org.mockito.Mockito.when;

public class UnitTest {
    @Mock
    private Connection connection;
    
    @Mock 
    private PreparedStatement pstmt; 
    
    @Mock
    private ResultSet res; 
    
    PostgreSQLDataAccess pda;
    
    @Before
    public void setUp() {
        pda = new PostgreSQLDataAccess(connection);
    }
    
    @Test
    public void testGetBookAuthorByCity() throws SQLException{
        
    }
    
}
