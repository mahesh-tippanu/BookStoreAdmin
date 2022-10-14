package com.example.bookstore_backend.repository;

import com.example.bookstore_backend.model.Order;
import com.example.bookstore_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//
@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query(value = "select * from bookstore.order_data where user_id= :id", nativeQuery = true)
    Order findByUserId(long id);
}