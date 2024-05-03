package mk.ukim.finki.uiktp.bookeshop.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.uiktp.bookeshop.model.Book;
import mk.ukim.finki.uiktp.bookeshop.model.CartItem;
import mk.ukim.finki.uiktp.bookeshop.model.CartItemKey;
import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {
    Optional<CartItem> findByShoppingCartAndBook(ShoppingCart cart, Book book);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.shoppingCart = :shoppingCart")
    void deleteByShoppingCart(@Param("shoppingCart") ShoppingCart shoppingCart);

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

}
