package mk.ukim.finki.uiktp.bookeshop.repository;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
