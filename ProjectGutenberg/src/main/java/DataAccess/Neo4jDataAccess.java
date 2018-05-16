package DataAccess;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class Neo4jDataAccess {
    
    // Getting book from database
    public static void getBook(Session session, String query) {
        StatementResult result = session.run(query);
        while (result.hasNext()) {
            Record record = result.next();
            System.out.println(record.get("name").asString());
        }
    }
    
}
