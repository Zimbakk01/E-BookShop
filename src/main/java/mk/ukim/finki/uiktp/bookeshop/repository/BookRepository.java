package mk.ukim.finki.uiktp.bookeshop.repository;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    List<Book> findBooksByGenre(Genre genre);
    List<Book> findBooksByAuthor(Author author);
    List<Book> findBooksByPriceBetween(float minPrice, float maxPrice);

    @Query("SELECT b FROM Book b WHERE b.author.author_id = :authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);

}
