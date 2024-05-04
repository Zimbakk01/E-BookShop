package mk.ukim.finki.uiktp.bookeshop.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.uiktp.bookeshop.mapper.BookMapper;
import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.dto.BookDto;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Genre;
import mk.ukim.finki.uiktp.bookeshop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public List<BookDto> findAll() {
        List<Book> books = this.bookService.findAll();
        return books.stream()
                .map(this.bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/isbn/{isbn}")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookDto> findBookByISBN(@PathVariable String isbn) {
        return this.bookService.findBookByISBN(isbn)
                .map(book -> ResponseEntity.ok().body(this.bookMapper.bookToBookDto(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
//    @PreAuthorize("isAuthenticated()")
    public List<BookDto> findBooksByGenre(@PathVariable Genre genre) {
        List<Book> books = this.bookService.findBooksByGenre(genre);
        return books.stream()
                .map(this.bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/author/{authorId}")
//    @PreAuthorize("isAuthenticated()")
    public List<BookDto> findBooksByAuthor(@PathVariable Long authorId) {
        List<Book> books = this.bookService.findBooksByAuthor(authorId);
        return books.stream()
                .map(this.bookMapper::bookToBookDto)
                .collect(Collectors.toList());    }
    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        Long authorId = bookDto.getAuthorID();


        return this.bookService.create(bookDto, authorId)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{isbn}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> update(@PathVariable String isbn,
                                       @RequestBody BookDto bookDto) {
        Long authorId = bookDto.getAuthorID();
        return this.bookService.update(isbn, bookDto, authorId)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{isbn}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteBookByIsbn(@PathVariable String isbn) {
        this.bookService.deleteBookByIsbn(isbn);
        if (this.bookService.findBookByISBN(isbn).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
