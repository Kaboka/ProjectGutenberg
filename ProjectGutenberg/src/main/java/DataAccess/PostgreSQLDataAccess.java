/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLDataAccess {

    public static List<Book> getBookAuthorByCity() throws SQLException {
        Connection connection = null;
        DriverManager driverManager = null;
        Statement statement;
        ResultSet resultSet;

        ArrayList<Book> books = new ArrayList();

        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }

        statement = connection.createStatement();
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

    public static void getCityByBook() throws SQLException {
        Connection connection = null;
        DriverManager driverManager = null;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }

        statement = connection.createStatement();
        String book_title = "Moby Dick";
        resultSet = statement.executeQuery("SELECT city_name\n"
                + "	FROM \"schemaGutenberg\".city AS city\n"
                + "	INNER JOIN \"schemaGutenberg\".\"book-city\" AS book_city\n"
                + "	ON (city.id = book_city.city_id)\n"
                + "	INNER JOIN \"schemaGutenberg\".book AS book\n"
                + "	ON (book_city.book_id = book.id)\n"
                + "	WHERE book_title = " + "'" + book_title + "'");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    public static void getBookAuthorCityByAuthor() throws SQLException {
        Connection connection = null;
        DriverManager driverManager = null;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }

        statement = connection.createStatement();
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
