package com.example.bookstore_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;


@Entity
@RequiredArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId", nullable = false)
    private long cartId;
    @ManyToOne
    @JoinColumn(name = "bookId")
    public Book book;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    public User user;
    public int quantity;
    public double totalPrice;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Cart(User user_Id, Book bookId, int quantity, double totalPrice) {
        this.book = bookId;
        this.user = user_Id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
