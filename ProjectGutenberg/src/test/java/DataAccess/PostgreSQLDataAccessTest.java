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
    PostgreSQLDataAccess dataAccess; 

    @InjectMocks
    private PostgreSQLDataAccess mockDataAccess;

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
        dataAccess = new PostgreSQLDataAccess(connector);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void getCitiesByBookTitleMoby() throws SQLException{
        String title = "Moby Dick";
        assertEquals("London", dataAccess.getCitiesByBookTitle(title).get(0).getName());
    }
    @Test
    public void getCitiesByBookTitleWizard() throws SQLException{
        String title = "Wizard of osThe Wonderful Wizard of Oz";
        assertEquals("China", dataAccess.getCitiesByBookTitle(title).get(0).getName());
    }

//    @Test
//    public void testGetByCityName() throws SQLException {
//        Mockito.when(con.createStatement()).thenReturn(stmt);
//        Mockito.when(stmt.executeQuery("")).thenReturn(resultSet);
//        stmt = con.createStatement();
//        ResultSet res = stmt.executeQuery("");
//        assertEquals(res, resultSet);
//        Mockito.verify(con).createStatement();
//    }
}