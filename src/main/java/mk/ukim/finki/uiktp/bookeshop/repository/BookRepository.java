package mk.ukim.finki.uiktp.bookeshop.repository;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    List<Book> findBooksByGenre(Genre genre);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);

}
