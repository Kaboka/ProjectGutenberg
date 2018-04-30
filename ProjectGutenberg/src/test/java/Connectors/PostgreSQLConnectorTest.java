package Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;

public class PostgreSQLConnectorTest {

    @Test()
    public void testDBConnector(Connection connection, DriverManager driverManager) {
        try {
            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }
    }

}
