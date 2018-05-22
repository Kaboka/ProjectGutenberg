package Facade;

import Controller.Facade;
import static Controller.Facade.dbType.NEO;
import static Controller.Facade.dbType.POSTGRESS;
import DataAccess.PostgreSQLDataAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTest {
    
    private Facade facade;
    
    @Mock
    private PostgreSQLDataAccess pda;
    
    @Mock
    private ArrayList res;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        facade = new Facade();
    }
    
    @Test
    public void testCorrectDataAccess() throws SQLException{
        when(pda.getBookAuthorByCity("")).thenReturn(res);
        facade.getBookAuthorByCity(POSTGRESS, "");
        verify(pda).getBookAuthorByCity("");
    }
    
}
