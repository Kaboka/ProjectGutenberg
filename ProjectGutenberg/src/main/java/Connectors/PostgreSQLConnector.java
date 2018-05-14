package Connectors;

import DataAccess.PostgreSQLDataAccess;
import java.sql.SQLException;

public class PostgreSQLConnector {

    public static void main(String[] args) throws SQLException {
        System.out.println("Get book title and author name by city name");
        PostgreSQLDataAccess.getBookAuthorByCity();
        System.out.println("-----------------------------------");
        System.out.println("Get city name by book title");
        PostgreSQLDataAccess.getCityByBook();
        System.out.println("-----------------------------------");
        System.out.println("Get book title and author name and city name by author name");
        PostgreSQLDataAccess.getBookAuthorCityByAuthor();
    }
}
