package mk.ukim.finki.uiktp.bookeshop.service;

import mk.ukim.finki.uiktp.bookeshop.model.Author;
import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<ShoppingCart> findAll();

    Optional<ShoppingCart> findById(Long id);

    ShoppingCart addBookToCart(String username, String bookIsbn, int quantity);


    ShoppingCart removeBookFromCart(String username, String bookIsbn);

    ShoppingCart updateBookQuantity(String username, String bookIsbn, int quantity);

    void clearCart(String username);

    List<Book> getCartContents(String username);
}

