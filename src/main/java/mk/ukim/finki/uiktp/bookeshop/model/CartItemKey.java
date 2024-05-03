package mk.ukim.finki.uiktp.bookeshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CartItemKey implements Serializable {

    @Column(name="book_isbn")
    String isbn;

    @Column(name="shopping_cart_id")
    Long shoppingCartId;

    public CartItemKey(Long shoppingCartId, String isbn) {
        this.shoppingCartId = shoppingCartId;
        this.isbn = isbn;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
