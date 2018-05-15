package DataAccess;

import Connectors.PostgreSQLConnector;
import Model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLDataAccess {
    
    private PostgreSQLConnector connection; 
    
    public PostgreSQLDataAccess(PostgreSQLConnector connection){
        this.connection = connection;
    }

    public List<Book> getBookAuthorByCity() throws SQLException {
        
        Statement statement;
        ResultSet resultSet;

        ArrayList<Book> books = new ArrayList();
        
        statement = connection.SQLConnector().createStatement();
        String city_name = "London";
        resultSet = statement.executeQuery("SELECT book_title, author_name\n"
                + "	FROM \"schemaGutenberg\".book AS book \n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-author\" AS book_author\n"
                + "	ON (book.id = book_author.book_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".author AS author\n"
                + "	ON (book_author.author_id = author.id)	\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (book.id = book_city.book_id)\n"
                + "	INNER JOIN  \"schemaGutenberg\".city AS city\n"
                + "	ON (book_city.city_id = city.id)\n"
                + "	WHERE city.city_name = " + "'" + city_name + "'");

        while (resultSet.next()) {
            books.add(new Book(resultSet.getString(1), resultSet.getString(2)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return books;
    }

//    public ArrayList<City> getCitiesByBookTitle(String title) throws SQLException {
//        ArrayList<City> cities = new ArrayList();
//        cities.add(new City("London"));
//        return cities; 
//    }

    public void getBookAuthorCityByAuthor() throws SQLException {
        
        Statement statement;
        ResultSet resultSet;

        statement = connection.SQLConnector().createStatement();
        String author_name = "L. Frank Baum";
        resultSet = statement.executeQuery("SELECT book_title, author_name, city_name\n"
                + "	FROM \"schemaGutenberg\".book AS book \n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-author\" AS book_author\n"
                + "	ON (book.id = book_author.book_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".author AS author\n"
                + "	ON (book_author.author_id = author.id)	\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (book.id = book_city.book_id)\n"
                + "	INNER JOIN  \"schemaGutenberg\".city AS city\n"
                + "	ON (book_city.city_id = city.id)\n"
                + "	WHERE author.author_name = " + "'" + author_name + "'");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
