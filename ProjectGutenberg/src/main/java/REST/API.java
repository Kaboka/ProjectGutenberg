package REST;

import Controller.Facade;
import Controller.Facade.dbType;
import Interfaces.FacadeInterface;
import Model.Book;
import Model.City;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @GET
    @Path("getBookAuthorByCity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorByCityName(@PathParam("id") String city_name) throws SQLException {
        List<Book> books = facade.getBookAuthorByCity(dbType.POSTGRESS, city_name);
        return new Gson().toJson(books);
    }

    @GET
    @Path("getCitiesByBookTitle/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCitiesByBookTitle(@PathParam("id") String book_title) throws SQLException {
        List<City> cities = facade.getCitiesByBookTitle(dbType.POSTGRESS, book_title);
        return new Gson().toJson(cities);
    }

    @GET
    @Path("getBookAuthorCityByAuthor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorCityByAuthor(@PathParam("id") String author_name) throws SQLException {
        List<Book> books = facade.getBookAuthorCityByAuthor(dbType.POSTGRESS, author_name);
        return new Gson().toJson(books);
    }

}
