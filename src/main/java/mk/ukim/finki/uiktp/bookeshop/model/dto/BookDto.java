package mk.ukim.finki.uiktp.bookeshop.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;

import java.util.List;

@Getter
@Setter
@Builder
@Data
public class BookDto {

    private String isbn;
    private String title;
    private String publicationHouse;
    private String publicationYear;
    private String genre;
    private float price;
    private String imageData;
    private Long authorID;

    public BookDto() {
    }

    public BookDto(String isbn, String title, String publicationHouse, String publicationYear, String genre, float price, String imageData, Long authorID) {
        this.isbn = isbn;
        this.title = title;
        this.publicationHouse = publicationHouse;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.price = price;
        this.imageData = imageData;
        this.authorID = authorID;
    }


}
