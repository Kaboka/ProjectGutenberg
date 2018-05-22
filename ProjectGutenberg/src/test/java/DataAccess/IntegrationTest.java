package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

    PostgreSQLDataAccess dataAccess;
    Connection con; 
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "1234";
    
    @Before
    public void setUp() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        dataAccess = new PostgreSQLDataAccess(con);
    }

    @Test
    public void testGetCitiesByBookTitle() throws SQLException {
        String title = "Moby Dick";
        String actual = dataAccess.getCitiesByBookTitle(title).get(0).getName();
        Assert.assertThat(actual, is("London"));
    }
    
    @Test
    public void testGetCitiesByBookTitleFail() throws SQLException {
        String title = "Harry Potter";
        int actual = dataAccess.getCitiesByBookTitle(title).size();
        Assert.assertThat(actual, is(0));
    }
    
    @Test
    public void testGetBookAuthorByCity() throws SQLException{
        String city = "London";
        String actual = dataAccess.getBookAuthorByCity(city).get(0).getTitle();
        Assert.assertThat(actual, is("Moby Dick"));
    }
    
    @Test
    public void testGetBookAuthorByCityFail() throws SQLException{
        String city = "Karlslunde";
        int actual = dataAccess.getBookAuthorByCity(city).size();
        Assert.assertThat(actual, is(0));
    }
    
    @Test
    public void testGetBookAuthorCityByAuthor() throws SQLException{
        String author = "L. Frank Baum";
        String actual = dataAccess.getBookAuthorCityByAuthor(author).get(0).getTitle();
        Assert.assertThat(actual, is("The Wonderful Wizard of Oz"));
    }
    
    @Test
    public void testGetBookAuthorCityByAuthorFail() throws SQLException{
        String author = "Merlin";
        int actual = dataAccess.getBookAuthorCityByAuthor(author).size();
        Assert.assertThat(actual, is(0));
    }
    
//    @Test
//    public void testGetBookCityByGeolocation(){
//    
//    }
}
