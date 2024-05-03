package mk.ukim.finki.uiktp.bookeshop.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class Book {

    @Id
    private String isbn;
    private String title;
    private String publicationHouse;
    private String publicationYear;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private String price;

    @Lob
    private byte[] imageData;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors;

    public Book(){

    }
    public Book(String isbn, String title, String publicationHouse, String publicationYear, Genre genre, String price,byte[] imageData, List<Author> authors) {
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

