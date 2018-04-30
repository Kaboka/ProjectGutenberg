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

/**
 *
 * @author louis
 */
public class PostgreSQLConnector {

    public static void DBConnector(Connection connection, DriverManager driverManager) {
        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }
    }

    public static void DBConnectorClose(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void getBook(Connection connection, DriverManager driverManager, Statement statement, ResultSet resultSet) throws SQLException {
        DBConnector(connection, driverManager);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM book.book");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
        }
        DBConnectorClose(connection, statement, resultSet);
    }
}
