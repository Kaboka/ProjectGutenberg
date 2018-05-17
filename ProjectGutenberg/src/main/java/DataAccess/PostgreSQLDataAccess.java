package DataAccess;

import Interfaces.DataAccessInterface;
import Model.Book;
import Model.City;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreSQLDataAccess implements DataAccessInterface {

    private final Connection connection;

    public PostgreSQLDataAccess(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<Book> getBookAuthorByCity(String city_name) throws SQLException {

        Statement statement;
        ResultSet resultSet;

        ArrayList<Book> books = new ArrayList();

        statement = connection.createStatement();
        // String city_name = "London";
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
                + "	WHERE city.city_name LIKE " + "'" + city_name + "%'");

        while (resultSet.next()) {
            books.add(new Book(resultSet.getString(1), resultSet.getString(2)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return books;
    }

    @Override
    public ArrayList<City> getCitiesByBookTitle(String book_title) throws SQLException {
//        ArrayList<City> cities = new ArrayList();
//        cities.add(new City("London"));
//
//        return cities;

        Statement statement;
        ResultSet resultSet;

        ArrayList<City> cities = new ArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT city_name\n"
                + "	FROM \"schemaGutenberg\".city AS city\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (city.id = book_city.city_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".book AS book\n"
                + "	ON (book_city.book_id = book.id)\n"
                + "	WHERE book_title = " + "'" + book_title + "'");

        while (resultSet.next()) {
            cities.add(new City(resultSet.getString(1)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return cities;
    }

    @Override
    public ArrayList<Book> getBookAuthorCityByAuthor(String author_name) throws SQLException {

        Statement statement;
        ResultSet resultSet;

        ArrayList<Book> books = new ArrayList();

        statement = connection.createStatement();
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
            books.add(new Book(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return books;
    }

    @Override
    public ArrayList<Book> getBookCityByGeolocation(String latitude, String longitude) throws SQLException {

        Statement statement;
        ResultSet resultSet;

        ArrayList<Book> books = new ArrayList();

        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT book_title, city_name \n"
                + "	FROM \"schemaGutenberg\".book AS book \n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (book.id = book_city.book_id)\n"
                + "	INNER JOIN  \"schemaGutenberg\".city AS city\n"
                + "	ON (book_city.city_id = city.id)\n"
                + "	WHERE city.latitude = " + "'" + latitude + "'\n"
                + "	AND city.longitude = " + "'" + longitude + "'");

        while (resultSet.next()) {
            books.add(new Book(resultSet.getString(1), resultSet.getString(2)));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return books;
    }
}
