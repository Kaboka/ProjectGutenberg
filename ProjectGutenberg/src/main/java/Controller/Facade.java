/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connectors.PostgreSQLConnector;
import DataAccess.Neo4jDataAccess;
import DataAccess.PostgreSQLDataAccess;
import Interfaces.DataAccessInterface;
import Interfaces.FacadeInterface;
import Model.Book;
import Model.City;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public class Facade implements FacadeInterface{

    public static enum dbType{NEO, POSTGRESS};
    private DataAccessInterface access; 
    PostgreSQLConnector connection = new PostgreSQLConnector();
    
    @Override
    public ArrayList<Book> getBookAuthorByCity(dbType db, String city) throws SQLException {
        
        if(db == dbType.NEO){
            access = new Neo4jDataAccess();
        }else{
            access = new PostgreSQLDataAccess(connection.SQLConnector());
        }
        return access.getBookAuthorByCity(city);
    }

    @Override
    public ArrayList<City> getCitiesByBookTitle(dbType dbEnum, String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getBookAuthorCityByAuthor(dbType dbEnum, String author) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
