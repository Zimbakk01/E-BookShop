package mk.ukim.finki.uiktp.bookeshop.mapper;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDto bookToBookDto(Book book){
      return BookDto.builder()
                .authorID(book.getAuthor().getAuthor_id())
                .genre(String.valueOf(book.getGenre()))
                .title(book.getTitle())
                .price(book.getPrice())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .publicationHouse(book.getPublicationHouse())
                .imageData(new String(book.getImageData()))
                .build();

    }
}
