/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author louis
 */
public class PostgreSQLConnector {

    // Fields
    public static Connection connection = null;
    public static DriverManager driverManager = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        getBook();
    }

    public static void DBConnector() {
        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }
    }

    public static void getBook() throws ClassNotFoundException, SQLException {
        DBConnector();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM book.book")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
            }
        }
        connection.close();
    }
}
