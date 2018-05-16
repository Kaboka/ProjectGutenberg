package DataAccess;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import Interfaces.DataAccessInterface;
import Model.Book;
import Model.City;
import java.sql.SQLException;
import java.util.ArrayList;

public class Neo4jDataAccess implements DataAccessInterface{

    @Override
    public ArrayList<Book> getBookAuthorByCity(String city) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<City> getCitiesByBookTitle(String title) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getBookAuthorCityByAuthor(String author) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getBookCityByGeolocation(String latitude, String longitude) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Getting book from database
    public static void getBook(Session session, String query) {
        StatementResult result = session.run(query);
        while (result.hasNext()) {
            Record record = result.next();
            System.out.println(record.get("name").asString());
        }
    }
}
