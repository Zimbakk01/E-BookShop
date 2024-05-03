package mk.ukim.finki.uiktp.bookeshop.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;

import java.util.List;
@Getter
@Setter
@Data
public class BookDto {

    private String isbn;
    private String title;
    private String publicationHouse;
    private String publicationYear;
    private Genre genre;
    private String price;
    private byte[] imageData;
    private List<Author> authors;

    public BookDto() {
    }

    public BookDto(String isbn, String title, String publicationHouse, String publicationYear, Genre genre, String price, byte[] imageData, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.publicationHouse = publicationHouse;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.price = price;
        this.imageData = imageData;
        this.authors = authors;
    }


}
