package DataAccess;

import Connectors.PostgreSQLConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
    PostgreSQLDataAccess dataAccess;

    @Mock
    private Connection con;
    @Mock
    private Statement stmt;
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataAccess = new PostgreSQLDataAccess(con);
    }

    @After
    public void tearDown() {
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

//    public void getCitiesByBookTitleMoby() throws SQLException {
//        String title = "Moby Dick";
//        assertEquals("London", dataAccess.getCitiesByBookTitle(title).get(0).getName());
//    }
//
//    @Test
//    public void getCitiesByBookTitleWizard() throws SQLException {
//        String title = "Wizard of osThe Wonderful Wizard of Oz";
//        assertNotEquals("China", dataAccess.getCitiesByBookTitle(title).get(0).getName());
//    }
    //private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String url = "jdbc:postgresql://localhost:5432/gutenberg";
    private final String username = "postgres";
    private final String password = "1234";

    @Test
    public void getBookAuthorByCity() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT book_title, author_name\n"
                + "	FROM \"schemaGutenberg\".book AS book \n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-author\" AS book_author\n"
                + "	ON (book.id = book_author.book_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".author AS author\n"
                + "	ON (book_author.author_id = author.id)	\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (book.id = book_city.book_id)\n"
                + "	INNER JOIN  \"schemaGutenberg\".city AS city\n"
                + "	ON (book_city.city_id = city.id)\n"
                + "	WHERE city.city_name = 'London'");

        String book_title = "";
        String author_name = "";

        while (resultSet.next()) {
            book_title = resultSet.getString(1);
            author_name = resultSet.getString(2);
        }

        assertThat(book_title, is(equalTo("The Wonderful Wizard of Oz")));
        assertThat(author_name, is(equalTo("L. Frank Baum")));

    }

    @Test
    public void getCitiesByBookTitleMobyDick() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT city_name\n"
                + "	FROM \"schemaGutenberg\".city AS city\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (city.id = book_city.city_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".book AS book\n"
                + "	ON (book_city.book_id = book.id)\n"
                + "	WHERE book_title = 'Moby Dick'");

        String city_name = "";
        while (resultSet.next()) {
            city_name = resultSet.getString(1);
        }

        assertThat(city_name, is(equalTo("London")));
    }

    @Test
    public void getBookAuthorCityByFrankBaum() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT book_title, author_name, city_name\n"
                + "	FROM \"schemaGutenberg\".book AS book \n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-author\" AS book_author\n"
                + "	ON (book.id = book_author.book_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".author AS author\n"
                + "	ON (book_author.author_id = author.id)	\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (book.id = book_city.book_id)\n"
                + "	INNER JOIN  \"schemaGutenberg\".city AS city\n"
                + "	ON (book_city.city_id = city.id)\n"
                + "	WHERE author.author_name = 'L. Frank Baum'");

        String book_title = "";
        String author_name = "";
        String city_name = "";

        while (resultSet.next()) {
            book_title = resultSet.getString(1);
            author_name = resultSet.getString(2);
            city_name = resultSet.getString(3);
        }

        assertThat(book_title, is(equalTo("Fortune Hunters in China")));
        assertThat(author_name, is(equalTo("L. Frank Baum")));
        assertThat(city_name, is(equalTo("China")));
    }
    
// MANGLER TEST DATA
//    @Test
//    public void getBookCityByGeolocation() throws SQLException {
//        Connection con = DriverManager.getConnection(url, username, password);
//        Statement stmt = con.createStatement();
//        
//        String latitude = "???";
//        String longitude = "???";
//        
//        ResultSet resultSet = stmt.executeQuery("SELECT book_title, city_name \n"
//                + "	FROM \"schemaGutenberg\".book AS book \n"
//                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
//                + "	ON (book.id = book_city.book_id)\n"
//                + "	INNER JOIN  \"schemaGutenberg\".city AS city\n"
//                + "	ON (book_city.city_id = city.id)\n"
//                + "	WHERE city.latitude = " + "'" + latitude + "'\n"
//                + "	AND city.longitude = " + "'" + longitude + "'");
//
//        String book_title = "";
//        String city_name = "";
//
//        while (resultSet.next()) {
//            book_title = resultSet.getString(1);
//            city_name = resultSet.getString(3);
//        }
//
//        assertThat(book_title, is(equalTo("Fortune Hunters in China")));
//        assertThat(city_name, is(equalTo("China")));
//    }

}
