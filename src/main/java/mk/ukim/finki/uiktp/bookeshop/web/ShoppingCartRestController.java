package mk.ukim.finki.uiktp.bookeshop.web;

import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.BookNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.uiktp.bookeshop.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/shopping-cart")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;

     public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ShoppingCart> findAll() {
        return this.shoppingCartService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public  ResponseEntity<ShoppingCart> findById(@PathVariable Long id) {
        return this.shoppingCartService.findById(id)
                .map(shoppingCart -> ResponseEntity.ok().body(shoppingCart))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-book")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ShoppingCart> addBookToCart(@RequestParam String username,
                                                      @RequestParam String bookIsbn,
                                                      @RequestParam int quantity) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addBookToCart(username, bookIsbn, quantity);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (UserNotFoundException | BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove-book")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ShoppingCart> removeBookFromCart(@RequestParam String username,
                                                           @RequestParam String bookIsbn) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.removeBookFromCart(username, bookIsbn);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (UserNotFoundException | BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-quantity")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ShoppingCart> updateBookQuantity(@RequestParam String username,
                                                           @RequestParam String bookIsbn,
                                                           @RequestParam int quantity) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.updateBookQuantity(username, bookIsbn, quantity);
            return ResponseEntity.ok().body(shoppingCart);
        } catch (UserNotFoundException | BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clear-cart/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity clearCart(@PathVariable String username) {
        try {
            this.shoppingCartService.clearCart(username);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/contents")
    @PreAuthorize("isAuthenticated()")
    public List<Book> getCartContents(@RequestParam String username) {
        return this.shoppingCartService.getCartContents(username);
    }
}
