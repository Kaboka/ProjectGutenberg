package DataAccess;

import Model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

    PostgreSQLDataAccess dataAccess;
    Connection con; 
    private final String url = "jdbc:postgresql://localhost:5432/gutenberg";
    private final String username = "postgres";
    private final String password = "1234";
    
    @Before
    public void setUp() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        dataAccess = new PostgreSQLDataAccess(con);
    }

    @Test
    public void testGetCitiesByBookTitle() throws SQLException {
        String title = "Alice's Adventures in Wonderland";
        String actual = dataAccess.getCitiesByBookTitle(title).get(0).getName();
        Assert.assertThat(actual, is(equalTo("London")));
    }
    
    @Test
    public void testGetCitiesByBookTitleFail() throws SQLException {
        String title = "Harry Potter";
        int actual = dataAccess.getCitiesByBookTitle(title).size();
        Assert.assertThat(actual, is(equalTo(0)));
    }
    
    @Test
    public void testGetBookAuthorByCity() throws SQLException{
        String city = "Berlin";
        String actual = dataAccess.getBookAuthorByCity(city).get(0).getTitle();
        Assert.assertThat(actual, is(equalTo("The 1990 CIA World Factbook")));
    }
    
    @Test
    public void testGetBookAuthorByCityFail() throws SQLException{
        String city = "Karlslunde";
        int actual = dataAccess.getBookAuthorByCity(city).size();
        Assert.assertThat(actual, is(equalTo(0)));
    }
    
    @Test
    public void testGetBookAuthorCityByAuthor() throws SQLException{
        String author = "Muir, John";
        ArrayList<Book> res = dataAccess.getBookAuthorCityByAuthor(author);
        String title = res.get(0).getTitle();
        String city = res.get(0).getCities().get(0).getName();
        Assert.assertThat(title, is(equalTo("The Mountains of California")));
        Assert.assertThat(city, is(equalTo("Santa Cruz")));
    }
    
    @Test
    public void testGetBookAuthorCityByAuthorFail() throws SQLException{
        String author = "Merlin";
        int actual = dataAccess.getBookAuthorCityByAuthor(author).size();
        Assert.assertThat(actual, is(equalTo(0)));
    }
    
    @Test
    public void testGetBookCityByGeolocation() throws SQLException{
        String longitude = "16.37208";
        String latitude = "48.20849"; 
        String actual = dataAccess.getBookCityByGeolocation(latitude, longitude).get(0).getAuthor();
    
        Assert.assertThat(actual, is(equalTo("Vienna")));
    }
}
