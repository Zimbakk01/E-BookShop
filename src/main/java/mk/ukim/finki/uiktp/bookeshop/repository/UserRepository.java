package mk.ukim.finki.uiktp.bookeshop.repository;

import mk.ukim.finki.uiktp.bookeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
