package Connectors;

import DataAccess.PostgreSQLDataAccess;
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

        try {

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gutenberg", "postgres", "1234");

            st = con.createStatement();

            DriverManager driverManager = null;
            
            PostgreSQLDataAccess.getByCityName(con, driverManager, st, rs);

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
}
