package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;
import mk.ukim.finki.uiktp.bookeshop.model.User;
import mk.ukim.finki.uiktp.bookeshop.model.dto.UserDto;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Role;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.UserRepository;
import mk.ukim.finki.uiktp.bookeshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> create(UserDto userDto) {
        String hashed=passwordEncoder.encode(userDto.getPassword());
        User user = new User(userDto.getUsername(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getAddress(),
                userDto.getPhoneNumber(),
                userDto.getRole(),
                hashed);
        this.userRepository.save(user);
        return Optional.of(user);

    }



    @Override
    public Optional<User> update(String username,UserDto userDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(user.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        return Optional.of(this.userRepository.save(user));

    }



//    @Override
//    public void delete(String username) {
//        userRepository.deleteByUsername(username);
//    }
@Override
public Optional<User> delete(String username) {
    Optional<User> user = userRepository.findByUsername(username);


    if (user.isPresent()) {
        userRepository.delete(user.get());
        return user;
    } else {
        return Optional.empty();
    }
}
}
