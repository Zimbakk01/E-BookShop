    package mk.ukim.finki.uiktp.bookeshop.service;

    import mk.ukim.finki.uiktp.bookeshop.model.Book;
    import mk.ukim.finki.uiktp.bookeshop.model.dto.BookDto;
    import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;

    import java.util.List;
    import java.util.Optional;

    public interface BookService {

        List<Book> findAll();

        Optional<Book> findBookByISBN(String isbn);

        List<Book> findBooksByGenre(Genre genre);

        List<Book> findBooksByAuthor(Long authorId);

        Optional<Book> create(BookDto bookDto);

        Optional<Book> update(String isbn,BookDto bookDto);

        void deleteBookByIsbn(String isbn);

    }
