package mk.ukim.finki.uiktp.bookeshop.service.Impl;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.CartItem;
import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;
import mk.ukim.finki.uiktp.bookeshop.model.User;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.BookNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.repository.BookRepository;
import mk.ukim.finki.uiktp.bookeshop.repository.CartItemRepository;
import mk.ukim.finki.uiktp.bookeshop.repository.ShoppingCartRepository;
import mk.ukim.finki.uiktp.bookeshop.repository.UserRepository;
import mk.ukim.finki.uiktp.bookeshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<ShoppingCart> findAll() {
        return this.shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public ShoppingCart addBookToCart(String username, String bookIsbn, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Book book = bookRepository.findById(bookIsbn)
                .orElseThrow(() -> new BookNotFoundException(bookIsbn));

        ShoppingCart cart = user.getShoppingCart();

        Optional<CartItem> existingItem = cartItemRepository.findByShoppingCartAndBook(cart, book);

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem newItem = new CartItem(cart, book, quantity);
            cartItemRepository.save(newItem);
        }

        return cart;
    }
    @Override
    public ShoppingCart removeBookFromCart(String username, String bookIsbn) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Book book = bookRepository.findById(bookIsbn)
                .orElseThrow(() -> new BookNotFoundException(bookIsbn));
        ShoppingCart cart = user.getShoppingCart();

        cartItemRepository.findByShoppingCartAndBook(cart, book)
                .ifPresent(cartItem -> cartItemRepository.delete(cartItem));

        return cart;
    }

    @Override
    public ShoppingCart updateBookQuantity(String username, String bookIsbn, int quantity) {
        if (quantity < 1) {
            return removeBookFromCart(username, bookIsbn);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Book book = bookRepository.findById(bookIsbn)
                .orElseThrow(() -> new BookNotFoundException(bookIsbn));
        ShoppingCart cart = user.getShoppingCart();

        cartItemRepository.findByShoppingCartAndBook(cart, book).ifPresentOrElse(cartItem -> {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }, () -> {
            CartItem newItem = new CartItem(cart, book, quantity);
            cartItemRepository.save(newItem);
        });
        return cart;
    }

    @Override
    public void clearCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        ShoppingCart cart = user.getShoppingCart();
        cartItemRepository.deleteByShoppingCart(cart);
    }

    @Override
    public List<Book> getCartContents(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        ShoppingCart cart = user.getShoppingCart();
        List<CartItem> cartItems = cartItemRepository.findByShoppingCart(cart);
        return cartItems.stream().map(CartItem::getBook).collect(Collectors.toList());
    }
}
