package REST;

import Controller.Facade;
import Controller.Facade.dbType;
import Interfaces.FacadeInterface;
import Model.Book;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")

public class API {
    FacadeInterface facade = new Facade();
    public API() {
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return new Gson().toJson("Hej");
    }

    @GET
    @Path("getBookAuthorByCity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorByCity() throws SQLException {
        List<Book> book = facade.getBookAuthorByCity(dbType.POSTGRESS, "London");
        return new Gson().toJson(book);
    }

}
