package Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLConnector {

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String query = "SELECT id, author_name\n"
                + "	FROM \"schemaGutenberg\".author;";

        try {

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");

            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2));
            }
            DriverManager driverManager = null;
            
            getBook(con, driverManager, st, rs);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            }
        }
    }

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
