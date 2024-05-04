package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.dto.BookDto;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.BookNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.uiktp.bookeshop.repository.BookRepository;
import mk.ukim.finki.uiktp.bookeshop.service.BookService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Base64;
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
    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findBooksByAuthor(author);
    }
    @Override
    public List<Book> findBooksByPriceBetween(float minPrice, float maxPrice){
        return bookRepository.findBooksByPriceBetween(minPrice, maxPrice);

    }
    @Override
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public Optional<Book> create(BookDto bookDto, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Genre bookGenre= Genre.valueOf(bookDto.getGenre());
        byte[] image= bookDto.getImageData().getBytes();

        Book book=Book.builder()
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .publicationHouse(bookDto.getPublicationHouse())
                .publicationYear(bookDto.getPublicationYear())
                .genre(bookGenre)
                .price(bookDto.getPrice())
                .imageData(image)
                .author(author).build();

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> update(String isbn, BookDto bookDto, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        Book book = this.bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
        Genre bookGenre= Genre.valueOf(bookDto.getGenre());
        byte[] image= bookDto.getImageData().getBytes();
        book.setTitle(bookDto.getTitle());
        book.setPublicationHouse(bookDto.getPublicationHouse());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setGenre(bookGenre);
        book.setPrice(bookDto.getPrice());
        book.setImageData(image);
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));
    }





    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
