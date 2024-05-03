package mk.ukim.finki.uiktp.bookeshop.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @EmbeddedId
    CartItemKey id;
    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    ShoppingCart shoppingCart;

    @ManyToOne
    @MapsId("isbn")
    @JoinColumn(name = "book_isbn")
    Book book;

    private int quantity;

    public CartItem(ShoppingCart shoppingCart, Book book, int quantity) {
        this.id = new CartItemKey(shoppingCart.getId(), book.getIsbn());
        this.shoppingCart = shoppingCart;
        this.book = book;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return this.book;
    }

    public CartItem() {
    }

}