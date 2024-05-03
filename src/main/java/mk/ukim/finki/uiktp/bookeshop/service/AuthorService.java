package mk.ukim.finki.uiktp.bookeshop.service;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(AuthorDto authorDto);

    Optional<Author> update(Long id, AuthorDto authorDto);

    void delete(Long id);

}
