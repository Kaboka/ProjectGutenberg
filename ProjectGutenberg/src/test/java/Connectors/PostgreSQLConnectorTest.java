//package Connectors;
//
//import java.sql.*;
//import org.junit.*;
//
//public class PostgreSQLConnectorTest {
//
//    private Connection connection;
//    private DriverManager driverManager;
//    private Statement statement;
//    private ResultSet resultSet;
//
//    @Before
//    public void before() {
//        try {
//            connection = driverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
//        } catch (SQLException ex) {
//            ex.toString();
//        }
//    }
//
//    @After
//    public void after() throws SQLException {
//        resultSet.close();
//        statement.close();
//        connection.close();
//    }
//
//
//
//}
