package mk.ukim.finki.uiktp.bookeshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;


    public  ShoppingCart(){

    }

    public Long getId() {
        return id;
    }

}
