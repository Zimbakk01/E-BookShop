package mk.ukim.finki.uiktp.bookeshop.service;

import mk.ukim.finki.uiktp.bookeshop.model.User;
import mk.ukim.finki.uiktp.bookeshop.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> create(UserDto userDto);

    Optional<User> update(String username,UserDto userDto);

//    void delete(String username);
    Optional<User> delete(String username);
}
