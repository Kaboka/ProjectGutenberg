/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

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
    
}
