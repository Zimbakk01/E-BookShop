package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.dto.AuthorDto;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.uiktp.bookeshop.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(AuthorDto author) {
        Author author1 = new Author(author.getName(),author.getSurname(), author.getBirthYear());
        this.authorRepository.save(author1);
        return Optional.of(author1);

    }

    @Override
    public Optional<Author> update(Long id, AuthorDto author) {
        Author author1 = this.authorRepository.findById(id)
                .orElseThrow(() ->new AuthorNotFoundException(id));
        author1.setName(author.getName());
        author1.setSurname(author.getSurname());
        author1.setBirthYear(author.getBirthYear());
        this.authorRepository.save(author1);
        return Optional.of(author1);
    }




    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }
}
