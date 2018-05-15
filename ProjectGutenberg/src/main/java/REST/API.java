package REST;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")

public class API {

    public API() {
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return new Gson().toJson("Hej");
    }

//    @GET
//    @Path("getBookAuthorByCity")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getBookAuthorByCity() throws SQLException {
//        List<Book> book = PostgreSQLDataAccess.getBookAuthorByCity();
//        return new Gson().toJson(book);
//    }

}
