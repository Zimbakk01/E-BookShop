package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.dto.BookDto;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.BookNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.uiktp.bookeshop.repository.BookRepository;
import mk.ukim.finki.uiktp.bookeshop.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookByISBN(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    @Override
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Book book = new Book(
                bookDto.getIsbn(),
                bookDto.getTitle(),
                bookDto.getPublicationHouse(),
                bookDto.getPublicationYear(),
                bookDto.getGenre(),
                bookDto.getPrice(),
                bookDto.getImageData(),
                bookDto.getAuthors()
        );

        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public Optional<Book> update(String isbn,BookDto bookDto) {
        Book book = this.bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));


        book.setTitle(bookDto.getTitle());
        book.setPublicationHouse(bookDto.getPublicationHouse());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setImageData(bookDto.getImageData());
        book.setAuthors(bookDto.getAuthors());

        return Optional.of(this.bookRepository.save(book));
    }





    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
