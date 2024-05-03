package mk.ukim.finki.uiktp.bookeshop.repository;

import mk.ukim.finki.uiktp.bookeshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

}
