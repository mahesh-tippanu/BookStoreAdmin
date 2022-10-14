package com.example.bookstore_backend.model;
import com.example.bookstore_backend.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;
    int quantity;
    double totalPrice;
    private String address;

    boolean cancel = false;
    public LocalDate orderDate = LocalDate.now();

    public Order( Cart cart, int quantity, double totalPrice,String address ) {
        this.cart = cart;
        this.orderDate = LocalDate.now();
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.address = address;
    }

}
