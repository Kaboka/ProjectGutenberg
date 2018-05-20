package REST;

import Controller.Facade;
import Controller.Facade.dbType;
import Exceptions.NotFoundExceptionMapper;
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

    @GET
    @Path("getBookAuthorByCity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorByCity(@PathParam("id") String city_name) throws SQLException, NotFoundExceptionMapper {
        List<Book> books = facade.getBookAuthorByCity(dbType.POSTGRESS, city_name);
        if (books.isEmpty()) {
            throw new NotFoundExceptionMapper("No books found with the given city name");
        }
        return new Gson().toJson(books);
    }

    @GET
    @Path("getCitiesByBookTitle/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCitiesByBookTitle(@PathParam("id") String book_title) throws SQLException, NotFoundExceptionMapper {
        List<City> cities = facade.getCitiesByBookTitle(dbType.POSTGRESS, book_title);
//        if (cities.isEmpty()) {
//            throw new NotFoundExceptionMapper("No cities found with the given book title");
//        }
        return new Gson().toJson(cities);
    }

    @GET
    @Path("getBookAuthorCityByAuthor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorCityByAuthor(@PathParam("id") String author_name) throws SQLException, NotFoundExceptionMapper {
        List<Book> books = facade.getBookAuthorCityByAuthor(dbType.POSTGRESS, author_name);
        if (books.isEmpty()) {
            throw new NotFoundExceptionMapper("No book found with the given author name");
        }
        return new Gson().toJson(books);
    }

    @GET
    @Path("getBookCityByGeolocation/{latitude}/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookCityByGeolocation(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude) throws SQLException, NotFoundExceptionMapper {
        List<Book> books = facade.getBookCityByGeolocation(dbType.POSTGRESS, latitude, longitude);
        if (books.isEmpty()) {
            throw new NotFoundExceptionMapper("No book found with the given geolocation");
        }
        return new Gson().toJson(books);
    }
}
