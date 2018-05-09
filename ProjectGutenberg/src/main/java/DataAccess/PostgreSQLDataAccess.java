/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import static Connectors.PostgreSQLConnector.DBConnector;
import static Connectors.PostgreSQLConnector.DBConnectorClose;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ehn19
 */
public class PostgreSQLDataAccess {
    
    public static void getBook(Connection connection, DriverManager driverManager, Statement statement, ResultSet resultSet) throws SQLException {
        DBConnector(connection, driverManager);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT id, author_name\n"
                + "	FROM \"schemaGutenberg\".author;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
        }
        DBConnectorClose(connection, statement, resultSet);
    }
}
