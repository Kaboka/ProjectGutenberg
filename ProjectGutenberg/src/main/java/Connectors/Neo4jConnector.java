package Connectors;

import org.neo4j.driver.v1.*;

public class Neo4jConnector {

    // Fields
    public static Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class"));
    public static Session session = driver.session();

    public static void main(String[] args) {
        DBConnector(driver, session);
//        getBook(session, "MATCH (s:Person) RETURN s.name AS name, s.from AS from");
        DBConnectorClose(driver, session);
    }

    // Connecting to Neo4j database
    public static void DBConnector(Driver driver, Session session) {
        try {
            driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class"));
            session = driver.session();
        } catch (Exception e) {
            System.out.println("Exception - " + e);
        }
    }

    // Closing connection to Neo4j database
    public static void DBConnectorClose(Driver driver, Session session) {
        session.close();
        driver.close();
    }
}
