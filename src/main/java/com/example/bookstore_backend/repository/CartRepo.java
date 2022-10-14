package com.example.bookstore_backend.repository;

import com.example.bookstore_backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM MyBookApp.cart where user_id = :id", nativeQuery = true)
    List<Cart> findCartsByUserId(long id);

    @Query(value = "select * from MyBookApp.cart where user_id = :id and book_id = :bookId", nativeQuery = true)
    Cart findCartsByUserIdAndBookId(long bookId, long id);
}