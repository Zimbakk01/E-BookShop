package mk.ukim.finki.uiktp.bookeshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String id){
        super(String.format("Book with isbn: %d was not found!"));
    }
}
