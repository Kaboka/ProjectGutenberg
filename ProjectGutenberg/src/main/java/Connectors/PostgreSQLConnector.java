package Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgreSQLConnector {

    private Connection con = null;
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "1234";

    public Connection SQLConnector() {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getSQLState());
        }
        return con;
    }

    public void close() throws SQLException {
        con.close();
    }

    private Connection dbConnection;

    public void getDBConnection() throws ClassNotFoundException, SQLException {
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");
        } catch (SQLException ex) {
            ex.toString();
        }
    }

    public int executeQuery(String query) throws ClassNotFoundException, SQLException {
        return dbConnection.createStatement().executeUpdate(query);
    }

    public static void main(String[] args) throws SQLException {
//    public static void main(String[] args) throws SQLException {
//        System.out.println("Get book title and author name by city name");
//        PostgreSQLDataAccess.getBookAuthorByCity();
//        System.out.println("-----------------------------------");
//        System.out.println("Get city name by book title");
//        PostgreSQLDataAccess.getCityByBook();
//        System.out.println("-----------------------------------");
//        System.out.println("Get book title and author name and city name by author name");
//        PostgreSQLDataAccess.getBookAuthorCityByAuthor();
//        System.out.println("  HER ");
//        ;
//
//        PostgreSQLDataAccess.test();
//        ArrayList<Book> list = null;
//        
//        list = PostgreSQLDataAccess.test();
//        for (Book b : list) {
//            System.out.println(b.getTitle());
//
//        }

//    }
    }
}
