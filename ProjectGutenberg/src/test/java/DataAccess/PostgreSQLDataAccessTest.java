<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Connectors.PostgreSQLConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ehn19
 */
@RunWith(MockitoJUnitRunner.class)
public class PostgreSQLDataAccessTest {

    PostgreSQLConnector connector = new PostgreSQLConnector();

    @InjectMocks
    private PostgreSQLDataAccess dataAccess;

    @Mock
    private Connection con;

    @Mock
    private Statement stmt;

    @Mock
    private ResultSet resultSet;

    public PostgreSQLDataAccessTest() {
    }

 
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetByCityName() throws SQLException {
        Mockito.when(con.createStatement()).thenReturn(stmt);
        Mockito.when(stmt.executeQuery("")).thenReturn(resultSet);
        stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("");
        assertEquals(res, resultSet);
        Mockito.verify(con).createStatement();
    }
}
=======
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package DataAccess;
//
//import Connectors.PostgreSQLConnector;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
///**
// *
// * @author ehn19
// */
//@RunWith(MockitoJUnitRunner.class)
//public class PostgreSQLDataAccessTest {
//    
//    @InjectMocks
//    private PostgreSQLDataAccess dataAccess;
//    
//    @Mock
//    private PostgreSQLConnector DBCon; 
//            
//    @Mock
//    private Connection con;
//    
//    @Mock
//    private Statement stmt;
//    
//    @Mock
//    private ResultSet resultSet; 
//    
//    public PostgreSQLDataAccessTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void testGetByCityName() throws SQLException {
//        String cityName; 
//        cityName = "Lyngby";
//        //Mockito.when();
//        Mockito.when(con.createStatement()).thenReturn(stmt);
//        Mockito.when(stmt.executeQuery("SELECT book_name, author FROM books WHERE cityName = " + cityName)).thenReturn(resultSet);
//        int conValue = con.createStatement().executeUpdate("SELECT book_name, author FROM books WHERE cityName = " + cityName);
//        
//        ResultSet value = stmt.executeQuery("SELECT book_name, author FROM books WHERE cityName = " + cityName);
//        assertEquals(value, resultSet);
//        assertEquals(conValue, 1);
//        //Mockito.verify(, Mockito.times(1));
//    }
//
//    
//}
>>>>>>> ce01304225ca726c6731a1317a330bc89768c17c
