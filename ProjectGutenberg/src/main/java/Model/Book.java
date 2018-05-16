package Model;

public class Book {

    private String book_title;
    private String author_name;
    private String city_name;

    public Book(){}
    
    public Book(String book_title, String author_name) {
        this.book_title = book_title;
        this.author_name = author_name;
    }

    public Book(String book_title, String author_name, String city_name) {
        this.book_title = book_title;
        this.author_name = author_name;
        this.city_name = city_name;
    }

    public String getTitle() {
        return book_title;
    }

    public void setTitle(String title) {
        this.book_title = title;
    }

    public String getAuthor() {
        return author_name;
    }

    public void setAuthor(String author) {
        this.author_name = author;
    }

}
