package Connectors;

import static Connectors.PostgreSQLConnector.DBConnector;
import static Connectors.PostgreSQLConnector.DBConnectorClose;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class PostgreSQLConnectorTest {

    private Connection connection;
    private DriverManager driverManager;
    private Statement statement;
    private ResultSet resultSet;

    @Before
    public void before() {
        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }
    }

    @After
    public void after() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test()
    public void DBConnection() throws SQLException {
        DBConnector(connection, driverManager);

        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT book_id FROM book.book WHERE book_id = 1");

        while (resultSet.next()) {
            assertNotNull(resultSet.getString(1));
        }

        DBConnectorClose(connection, statement, resultSet);
    }

    @Test()
    public void getBook() throws SQLException {
        DBConnector(connection, driverManager);
        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT book_id, title FROM book.book WHERE book_id = 1 AND title = 'H'");
        while (resultSet.next()) {
            assertThat(resultSet.getString(1), is(equalTo("1")));
            assertThat(resultSet.getString(2), is(equalTo("H")));
        }

        DBConnectorClose(connection, statement, resultSet);
    }

}
